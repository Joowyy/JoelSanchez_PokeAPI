package com.example.joelsanchez_pokeapi.repository

import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.model.PokemonType

class PokemonRepository {

    private val listaPokemon: MutableList<Pokemon> = mutableListOf(
        Pokemon(4,  "Charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
            listOf(PokemonType("Fuego")), 0.6f, 8.5f),
        Pokemon(5,  "Charmeleon", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
            listOf(PokemonType("Fuego")), 1.1f, 19.0f),
        Pokemon(6,  "Charizard",  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
            listOf(PokemonType("Fuego"), PokemonType("Volador")), 1.7f, 90.5f),
        Pokemon(7,  "Squirtle",   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
            listOf(PokemonType("Agua")), 0.5f, 9.0f),
        Pokemon(8,  "Wartortle",  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
            listOf(PokemonType("Agua")), 1.0f, 22.5f),
        Pokemon(9,  "Blastoise",  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
            listOf(PokemonType("Agua")), 1.6f, 85.5f),
        Pokemon(1,  "Bulbasaur",  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            listOf(PokemonType("Planta"), PokemonType("Veneno")), 0.7f, 6.9f),
        Pokemon(2,  "Ivysaur",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            listOf(PokemonType("Planta"), PokemonType("Veneno")), 1.0f, 13.0f),
        Pokemon(3,  "Venusaur",   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
            listOf(PokemonType("Planta"), PokemonType("Veneno")), 2.0f, 100.0f),
        Pokemon(25, "Pikachu",    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            listOf(PokemonType("Eléctrico")), 0.4f, 6.0f)
    )

    fun actualizarPokemonREP(pokemon: Pokemon?) {
        val posicion = listaPokemon.indexOf(pokemon)
        listaPokemon[posicion] = pokemon!!
    }

    fun eliminarPokemon(pokemon: Pokemon?) {
        listaPokemon.remove(pokemon)
    }

    fun getPokemons(): MutableList<Pokemon> = listaPokemon

    fun getPokemonsPorNombre(texto: String): List<Pokemon> {
        return listaPokemon.filter {
            it.nombre?.lowercase()?.contains(texto.lowercase().trim()) == true
        }
    }
}
