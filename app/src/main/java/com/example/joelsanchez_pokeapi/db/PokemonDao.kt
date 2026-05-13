package com.example.joelsanchez_pokeapi.db

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.Query

@Dao
interface PokemonDao {

    // Queries → devuelven LiveData, Room las ejecuta en background automáticamente
    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun getAll(): LiveData<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE favorito = 1 ORDER BY id ASC")
    fun getFavoritos(): LiveData<List<PokemonEntity>>

    // Writes → sin LiveData, se ejecutan via Executor en el Repository
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemons: List<PokemonEntity>)

    @Update
    fun update(pokemon: PokemonEntity)

    @Delete
    fun delete(pokemon: PokemonEntity)
}
