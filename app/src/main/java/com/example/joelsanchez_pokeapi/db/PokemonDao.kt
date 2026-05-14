package com.example.joelsanchez_pokeapi.db

import androidx.room.*

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun getAllSync(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemons: List<PokemonEntity>)

    @Update
    fun update(pokemon: PokemonEntity)

    @Delete
    fun delete(pokemon: PokemonEntity)
}
