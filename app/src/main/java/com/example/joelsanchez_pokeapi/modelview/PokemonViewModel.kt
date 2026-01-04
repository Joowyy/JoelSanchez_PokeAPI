package com.example.joelsanchez_pokeapi.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.repository.PokemonRepository

class PokemonViewModel : ViewModel() {

    private var repository : PokemonRepository = PokemonRepository()
    var pokemons = MutableLiveData<List<Pokemon>>()
    var pokemonSeleccionado = MutableLiveData<Pokemon>()

    // Obtenemos la lista de la clase Pokemon, y este lista MutableLiveData
    // Sera la que cambie los datos para luego devolverlos a la lista normal.
    fun obtenerPokemons () {

        pokemons.value = repository.getPokemons()

    }

    // Una vez se hayan realizado cambios, el primer actualizarPokemon llevara a este método
    // Que una vez ejecutado, efectuará los cambios en el Repository
    fun actualizarPokemonVIEW (pokemon : Pokemon) {

        repository.actualizarPokemonREP(pokemon)

    }

    fun eliminarPokemonVIEW(position: Int) {
        val listaActual = pokemons.value ?: return

        if (position !in listaActual.indices) return

        val eliminado = listaActual[position]
        repository.eliminarPokemon(eliminado)
        pokemons.value = repository.getPokemons()
    }

    fun seleccionarPokemon (pokemon : Pokemon) {

        pokemonSeleccionado.value = pokemon

    }

    fun buscarAnimalPorNombre(texto: String) {
        pokemons.setValue(repository.getPokemonsPorNombre(texto))
    }

}