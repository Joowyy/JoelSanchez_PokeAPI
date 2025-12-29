package com.example.joelsanchez_pokeapi.adapter

import com.example.joelsanchez_pokeapi.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.compose.ui.layout.Layout
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.joelsanchez_pokeapi.databinding.ViewholderPokemonBinding
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.modelview.PokemonViewModel

class PokemonAdapter (context : Context?, pokemons : List<Pokemon>, viewModel : PokemonViewModel)
    : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder?>() {

    private val inflater : LayoutInflater
    private var viewModel : PokemonViewModel
    private var pokemons : List<Pokemon>

    // Inicializamos las variables con el constructor
    init {

        this.inflater = LayoutInflater.from(context)
        this.viewModel = viewModel
        this.pokemons = pokemons

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {

        // Inflamos la vista de la CardView
        val view : View = inflater.inflate(R.layout.viewholder_pokemon, parent, false)
        return PokemonViewHolder(view)

    }

    override fun onBindViewHolder(@NonNull holder: PokemonViewHolder, position: Int) {

        val pokemon = pokemons!![position]

        holder.binding.nombre.text = pokemon.nombre
        holder.binding.imagen.setImageResource(pokemon.imagen)
        holder.binding.tipo1.text = pokemon.tipo1
        holder.binding.tipo2.text = pokemon.tipo2

        holder.itemView.setOnClickListener { v ->

            viewModel.seleccionarPokemon(pokemon)

            val navController = findNavController(v)
            navController.navigate(R.id.detallesPokemonFragment)

        }

        cambiarIconoFAV(pokemon, holder)
        holder.binding.iconFavorite.setOnClickListener { v ->  cambiarATTFav(pokemon, holder) }

    }

    fun establecerLista (pokemons : List<Pokemon>?) {

        if (pokemons != null) {
            this.pokemons = pokemons
        }
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {

        return if (pokemons != null) pokemons!!.size else 0

    }

    private fun cambiarATTFav (pokemon : Pokemon, holder : PokemonViewHolder) {

        // Mod. el atributo "favorito" de cada pokemon
        if (pokemon.favorito) {

            pokemon.camAtributoFavorito(false)

        } else {

            pokemon.camAtributoFavorito(true)

        }

        // Cambiamos la imagen de la estrella
        cambiarIconoFAV(pokemon, holder)

        // Y actualizamos el pokemon en su lista
        viewModel.actualizarPokemonVIEW(pokemon)

    }

    private fun cambiarIconoFAV (pokemon : Pokemon, holder : PokemonViewHolder) {

        // Segun como este el atributo favorito, mete una imagen u otra de la estrella
        if (pokemon.favorito) {

            holder.binding.iconFavorite.setImageResource(R.drawable.estrella_rellena)

        } else {

            holder.binding.iconFavorite.setImageResource(R.drawable.estrella_vacia)

        }

    }

    class PokemonViewHolder(@NonNull itemView : View) : RecyclerView.ViewHolder(itemView) {

        var binding : ViewholderPokemonBinding = ViewholderPokemonBinding.bind(itemView)

    }

}