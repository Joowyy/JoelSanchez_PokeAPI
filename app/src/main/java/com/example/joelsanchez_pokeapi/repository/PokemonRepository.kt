package com.example.joelsanchez_pokeapi.repository

import android.content.Context
import com.example.joelsanchez_pokeapi.db.PokemonDatabase
import com.example.joelsanchez_pokeapi.mapper.PokemonMapper
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.model.PokemonListApi
import com.example.joelsanchez_pokeapi.remote.Resource
import com.example.joelsanchez_pokeapi.remote.RetrofitClient
import com.example.joelsanchez_pokeapi.utils.ConnectivityHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

class PokemonRepository(context: Context) {

    private val api = RetrofitClient.pokemonApi
    private val dao = PokemonDatabase.getInstance(context).pokemonDao()
    private val executor = Executors.newSingleThreadExecutor()
    private val ctx = context.applicationContext

    private val listaPokemon: MutableList<Pokemon> = mutableListOf()

    fun cargarPokemons(limit: Int, callback: (Resource<List<Pokemon>>) -> Unit) {
        callback(Resource.loading())

        if (ConnectivityHelper.tieneConexion(ctx)) {
            cargarDesdeApi(limit, callback)
        } else {
            cargarDesdeRoom(callback)
        }
    }

    private fun cargarDesdeApi(limit: Int, callback: (Resource<List<Pokemon>>) -> Unit) {
        api.getPokemons(limit).enqueue(object : Callback<PokemonListApi> {
            override fun onResponse(call: Call<PokemonListApi>, response: Response<PokemonListApi>) {
                val items = response.body()?.results
                if (items.isNullOrEmpty()) {
                    callback(Resource.error("Sin datos"))
                    return
                }

                val resultados = mutableListOf<Pokemon>()
                val pendientes = AtomicInteger(items.size)

                items.forEach { item ->
                    api.getPokemonByName(item.name).enqueue(object : Callback<Pokemon> {
                        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                            response.body()?.let { synchronized(resultados) { resultados.add(it) } }
                            if (pendientes.decrementAndGet() == 0) entregarResultados(resultados, callback)
                        }

                        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                            if (pendientes.decrementAndGet() == 0) entregarResultados(resultados, callback)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<PokemonListApi>, t: Throwable) {
                // Sin conexión → intentar Room como fallback
                cargarDesdeRoom(callback)
            }
        })
    }

    private fun cargarDesdeRoom(callback: (Resource<List<Pokemon>>) -> Unit) {
        executor.execute {
            val entities = dao.getAllSync()
            if (entities.isEmpty()) {
                callback(Resource.error("Sin conexión y sin datos guardados"))
            } else {
                listaPokemon.clear()
                listaPokemon.addAll(PokemonMapper.entityListToDomain(entities))
                callback(Resource.success(listaPokemon))
            }
        }
    }

    private fun entregarResultados(resultados: List<Pokemon>, callback: (Resource<List<Pokemon>>) -> Unit) {
        val ordenados = resultados.sortedBy { it.id }
        executor.execute {

            // Preservar favoritos guardados en Room antes de sobrescribir
            val favoritosIds = dao.getAllSync().filter { it.favorito }.map { it.id }.toSet()
            ordenados.forEach { if (it.id in favoritosIds) it.favorito = true }

            listaPokemon.clear()
            listaPokemon.addAll(ordenados)
            dao.insertAll(PokemonMapper.domainListToEntity(listaPokemon))
            callback(Resource.success(listaPokemon))
            
        }
    }

    fun actualizarPokemonREP(pokemon: Pokemon?) {
        val posicion = listaPokemon.indexOfFirst { it.id == pokemon?.id }
        if (posicion >= 0) {
            listaPokemon[posicion] = pokemon!!
            executor.execute { dao.update(PokemonMapper.domainToEntity(pokemon)) }
        }
    }

    fun eliminarPokemon(pokemon: Pokemon?) {
        listaPokemon.remove(pokemon)
        pokemon?.let { executor.execute { dao.delete(PokemonMapper.domainToEntity(it)) } }
    }

    fun getPokemons(): MutableList<Pokemon> = listaPokemon

    fun getPokemonsPorNombre(texto: String): List<Pokemon> =
        listaPokemon.filter { it.name?.lowercase()?.contains(texto.lowercase().trim()) == true }

    fun getFavoritos(): List<Pokemon> = listaPokemon.filter { it.favorito }

    fun getFavoritosPorNombre(texto: String): List<Pokemon> {
        return if (texto.isBlank()) getFavoritos()
        else listaPokemon.filter {
            it.favorito && it.name?.lowercase()?.contains(texto.lowercase().trim()) == true
        }
    }
}
