package com.example.joelsanchez_pokeapi.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val imagen: String?,
    val tipo1: String?,
    val tipo2: String?,
    val height: Int,
    val weight: Int,
    var favorito: Boolean
)
