package com.example.joelsanchez_pokeapi.model

data class Pokemon(
    val id: Int,
    val nombre: String?,
    val imagen: String?,
    val tipos: List<PokemonType> = emptyList(),
    val altura: Float = 0f,
    val peso: Float = 0f,
    var favorito: Boolean = false
)
