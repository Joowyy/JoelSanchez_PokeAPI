package com.example.joelsanchez_pokeapi.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.model.PokemonType

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
) {

    // PokemonEntity → Pokemon (para mostrarlo en la UI)
    fun toPokemon(): Pokemon {
        val tipos = listOfNotNull(
            tipo1?.let { PokemonType(it) },
            tipo2?.let { PokemonType(it) }
        )
        return Pokemon(
            id       = id,
            name     = name,
            sprites  = Pokemon.Sprites(frontDefault = imagen),
            types    = tipos,
            height   = height,
            weight   = weight,
            favorito = favorito
        )
    }

    companion object {
        // Pokemon (viene de la API) → PokemonEntity (para guardar en Room)
        fun fromPokemon(pokemon: Pokemon): PokemonEntity {
            return PokemonEntity(
                id       = pokemon.id,
                name     = pokemon.name,
                imagen   = pokemon.imagen,
                tipo1    = pokemon.types.getOrNull(0)?.type?.name,
                tipo2    = pokemon.types.getOrNull(1)?.type?.name,
                height   = pokemon.height,
                weight   = pokemon.weight,
                favorito = pokemon.favorito
            )
        }

    }
}
