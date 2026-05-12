package com.example.joelsanchez_pokeapi.repository

import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.model.PokemonListApi
import com.example.joelsanchez_pokeapi.remote.Resource
import com.example.joelsanchez_pokeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.atomic.AtomicInteger

class PokemonRepository {

    private val api = RetrofitClient.pokemonApi
    private val listaPokemon: MutableList<Pokemon> = mutableListOf()

    fun cargarPokemons(limit: Int, callback: (Resource<List<Pokemon>>) -> Unit) {
        callback(Resource.loading())

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
                callback(Resource.error("Error de red: ${t.message}"))
            }
        })
    }

    private fun entregarResultados(resultados: List<Pokemon>, callback: (Resource<List<Pokemon>>) -> Unit) {
        listaPokemon.clear()
        listaPokemon.addAll(resultados.sortedBy { it.id })
        callback(Resource.success(listaPokemon))
    }

    fun actualizarPokemonREP(pokemon: Pokemon?) {
        val posicion = listaPokemon.indexOf(pokemon)
        if (posicion >= 0) listaPokemon[posicion] = pokemon!!
    }

    fun eliminarPokemon(pokemon: Pokemon?) {
        listaPokemon.remove(pokemon)
    }

    fun getPokemons(): MutableList<Pokemon> = listaPokemon

    fun getPokemonsPorNombre(texto: String): List<Pokemon> {
        return listaPokemon.filter {
            it.name?.lowercase()?.contains(texto.lowercase().trim()) == true
        }
    }

    fun getFavoritos(): List<Pokemon> = listaPokemon.filter { it.favorito }

    fun getFavoritosPorNombre(texto: String): List<Pokemon> {
        return if (texto.isBlank()) getFavoritos()
        else listaPokemon.filter {
            it.favorito && it.name?.lowercase()?.contains(texto.lowercase().trim()) == true
        }
    }
}
