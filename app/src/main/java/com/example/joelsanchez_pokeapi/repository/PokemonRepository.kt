package com.example.joelsanchez_pokeapi.repository

import com.example.joelsanchez_pokeapi.R
import com.example.joelsanchez_pokeapi.model.Pokemon


class PokemonRepository {

    private val listaPokemon : MutableList<Pokemon> = mutableListOf(
        Pokemon(4,  "Charmander", R.drawable.charmander, "Largatija de fuego",    "Fuego",    "",       false),
        Pokemon(5,  "Charmeleon", R.drawable.charmeleon, "Lagarton llamoso",       "Fuego",    "",       false),
        Pokemon(6,  "Charizard",  R.drawable.charizard,  "Gozdilla volcánico",     "Fuego",    "Volador",false),
        Pokemon(7,  "Squirtle",   R.drawable.squirtle,   "Tortuga cojonera",       "Agua",     "",       false),
        Pokemon(8,  "Wartortle",  R.drawable.wartortle,  "Tortuga fachera",        "Agua",     "",       false),
        Pokemon(9,  "Blastoise",  R.drawable.blastoise,  "Tortugon boina verde",   "Agua",     "",       false),
        Pokemon(1,  "Bulbasaur",  R.drawable.bulbasaur,  "Capullo cuatro patas",   "Planta",   "",       false),
        Pokemon(2,  "Ivysaur",    R.drawable.ivysaur,    "Mega capullo",           "Planta",   "Veneno", false),
        Pokemon(3,  "Venusaur",   R.drawable.venusaur,   "Oso capullo venenoso",   "Planta",   "Veneno", false),
        Pokemon(25, "Pikachu",    R.drawable.pikachu,    "Rata electrica",         "Eléctrico","",       false)
    )

    fun actualizarPokemonREP (pokemon : Pokemon?) {

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

    fun getPokemonsPorNombre(texto: String): List<Pokemon> {

        val resultado = mutableListOf<Pokemon>()

        for (a in listaPokemon) {

            if (a.nombre?.lowercase()?.contains(texto.lowercase().trim()) == true) {

                resultado.add(a)

            }

        }

        return resultado
    }


}