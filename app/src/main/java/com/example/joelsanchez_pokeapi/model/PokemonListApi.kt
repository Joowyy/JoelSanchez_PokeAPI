package com.example.joelsanchez_pokeapi.model

data class PokemonListApi(
    val count: Int,
    val results: List<Item>
) {
    data class Item(val name: String, val url: String)
}
