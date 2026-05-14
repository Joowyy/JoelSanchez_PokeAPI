package com.example.joelsanchez_pokeapi.mapper

import com.example.joelsanchez_pokeapi.db.PokemonEntity
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.model.PokemonType

object PokemonMapper {

    // Room (PokemonEntity) -> Dominio (Pokemon) para mostrar en la UI
    fun entityToDomain(entity: PokemonEntity): Pokemon {
        val tipos = listOfNotNull(
            entity.tipo1?.let { PokemonType(it) },
            entity.tipo2?.let { PokemonType(it) }
        )
        return Pokemon(
            id       = entity.id,
            name     = entity.name,
            sprites  = Pokemon.Sprites(frontDefault = entity.imagen),
            types    = tipos,
            height   = entity.height,
            weight   = entity.weight,
            favorito = entity.favorito
        )
    }

    // Dominio (Pokemon) -> Room (PokemonEntity) para guardar en BBDD
    fun domainToEntity(pokemon: Pokemon): PokemonEntity {
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

    // Versiones de lista
    fun entityListToDomain(list: List<PokemonEntity>): List<Pokemon> =
        list.map { entityToDomain(it) }

    fun domainListToEntity(list: List<Pokemon>): List<PokemonEntity> =
        list.map { domainToEntity(it) }
}
