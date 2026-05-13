package com.example.joelsanchez_pokeapi.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joelsanchez_pokeapi.model.Pokemon

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val imagen: String?,
    val tipos: String,      // nombres de tipo separados por coma: "fire,flying"
    val height: Int,
    val weight: Int,
    var favorito: Boolean
) {
    // Convierte PokemonEntity → Pokemon para el ViewModel/UI
    fun toPokemon(): Pokemon = TODO()

    companion object {
        // Convierte Pokemon → PokemonEntity para guardar en Room
        fun fromPokemon(pokemon: Pokemon): PokemonEntity = TODO()
    }
}
