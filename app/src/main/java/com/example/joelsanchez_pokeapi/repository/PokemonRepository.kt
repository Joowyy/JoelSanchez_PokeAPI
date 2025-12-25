package com.example.joelsanchez_pokeapi.repository

import com.example.joelsanchez_pokeapi.R
import com.example.joelsanchez_pokeapi.model.Pokemon

class PokemonRepository {

    private val listaPokemon : MutableList<Pokemon> = mutableListOf(

        Pokemon("Pikachu", R.drawable.pikachu, "Rata electrica", "Eléctrico", false)

    )

    fun actualizarPokemon (pokemon : Pokemon?) {

        // Recuperamos la posicion en la que se encuentra el pokemon cambiado.
        val posicion = listaPokemon.indexOf(pokemon)

        // Y le aniadimos las modificaciones que se llevaron a cabo.
        listaPokemon[posicion] = pokemon!!

    }

    fun eliminarPokemon (pokemon : Pokemon?) {

        listaPokemon.remove(pokemon)

    }

    // Getter de Pokemons
    fun getPokemons() : MutableList<Pokemon> = listaPokemon

}