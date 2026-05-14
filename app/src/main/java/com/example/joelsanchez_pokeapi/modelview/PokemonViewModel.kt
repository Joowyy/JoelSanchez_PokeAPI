package com.example.joelsanchez_pokeapi.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.remote.Resource
import com.example.joelsanchez_pokeapi.repository.PokemonRepository

class PokemonViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = PokemonRepository(app.applicationContext)
    val pokemons = MutableLiveData<List<Pokemon>>()
    val favoritosPokemons = MutableLiveData<List<Pokemon>>()
    val pokemonSeleccionado = MutableLiveData<Pokemon>()
    val estadoCarga = MutableLiveData<Resource<List<Pokemon>>>()
    private var datosCargados = false
    private var queryPokemons = ""
    private var queryFavoritos = ""

    fun obtenerPokemons() {
        if (datosCargados) return
        repository.cargarPokemons(151) { result ->
            estadoCarga.postValue(result)
            if (result.status == Resource.Status.SUCCESS) {
                datosCargados = true
                pokemons.postValue(result.data ?: emptyList())
            }
        }
    }

    fun actualizarPokemonVIEW(pokemon: Pokemon) {
        repository.actualizarPokemonREP(pokemon)
    }

    fun eliminarPokemonVIEW(pokemon: Pokemon) {
        repository.eliminarPokemon(pokemon)
        pokemons.value = repository.getPokemonsPorNombre(queryPokemons)
        favoritosPokemons.postValue(repository.getFavoritosPorNombre(queryFavoritos))
    }

    fun seleccionarPokemon(pokemon: Pokemon) {
        pokemonSeleccionado.value = pokemon
    }

    fun buscarPokemonPorNombre(texto: String) {
        queryPokemons = texto
        pokemons.postValue(repository.getPokemonsPorNombre(texto))
    }

    fun obtenerFavoritos() {
        favoritosPokemons.postValue(repository.getFavoritos())
    }

    fun buscarFavoritoPorNombre(texto: String) {
        queryFavoritos = texto
        favoritosPokemons.postValue(repository.getFavoritosPorNombre(texto))
    }
}
