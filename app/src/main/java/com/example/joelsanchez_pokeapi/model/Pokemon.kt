package com.example.joelsanchez_pokeapi.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int = 0,
    val name: String? = null,
    val sprites: Sprites? = null,
    val types: List<PokemonType> = emptyList(),
    val height: Int = 0,
    val weight: Int = 0,
    var favorito: Boolean = false
) {
    val imagen: String? get() = sprites?.frontDefault
    val altura: Int get() = height
    val peso: Int get() = weight

    data class Sprites(
        @SerializedName("front_default") val frontDefault: String?
    )
}
