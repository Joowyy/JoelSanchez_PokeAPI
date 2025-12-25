package com.example.joelsanchez_pokeapi.modelview

import androidx.lifecycle.MutableLiveData
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.repository.PokemonRepository

class PokemonViewModel {

    private lateinit var repository : PokemonRepository
    private var pokemons = MutableLiveData<List<Pokemon>>()

    // Obtenemos la lista de la clase Pokemon, y este lista MutableLiveData
    // Sera la que cambie los datos para luego devolverlos a la lista normal.
    fun obtenerPokemons () {

        pokemons.value = repository.getPokemons()

    }

}