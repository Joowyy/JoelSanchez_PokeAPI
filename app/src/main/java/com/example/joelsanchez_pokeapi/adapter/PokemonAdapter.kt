package com.example.joelsanchez_pokeapi.adapter

import com.example.joelsanchez_pokeapi.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.joelsanchez_pokeapi.databinding.ViewholderPokemonBinding
import com.example.joelsanchez_pokeapi.model.Pokemon

class PokemonAdapter (context : Context?, pokemons : List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder?>() {

    private val inflater : LayoutInflater

    // Inicializamos las variables con el constructor
    init {

        this.inflater = LayoutInflater.from(context)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.PokemonViewHolder {

        // Inflamos la vista de la CardView
        val view : View = inflater.inflate(R.layout.viewholder_pokemon, parent, false)
        return PokemonViewHolder(view)

    }

    override fun onBindViewHolder(holder: PokemonAdapter.PokemonViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

        return TODO("Provide the return value")
    }

    class PokemonViewHolder(@NonNull itemView : View) : RecyclerView.ViewHolder(itemView) {

        var binding : ViewholderPokemonBinding = ViewholderPokemonBinding.bind(itemView)

    }

}