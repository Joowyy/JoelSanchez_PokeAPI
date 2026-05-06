package com.example.joelsanchez_pokeapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.joelsanchez_pokeapi.R
import com.example.joelsanchez_pokeapi.databinding.ViewholderPokemonBinding
import com.example.joelsanchez_pokeapi.model.Pokemon
import com.example.joelsanchez_pokeapi.model.PokemonType
import com.example.joelsanchez_pokeapi.modelview.PokemonViewModel

class PokemonAdapter(context: Context?, pokemons: List<Pokemon>, viewModel: PokemonViewModel)
    : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder?>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var viewModel: PokemonViewModel = viewModel
    private var pokemons: List<Pokemon> = pokemons

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view: View = inflater.inflate(R.layout.viewholder_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]

        Glide.with(holder.itemView.context).load(pokemon.imagen).into(holder.binding.imagen)
        holder.binding.nombre.text = pokemon.nombre
        holder.binding.tvNumero.text = "#${String.format("%03d", pokemon.id)}"
        holder.binding.tvAltura.text = "${pokemon.altura}m"
        holder.binding.tvPeso.text = "${pokemon.peso}kg"

        val tipo1 = pokemon.tipos.getOrNull(0)
        val tipo2 = pokemon.tipos.getOrNull(1)

        if (tipo1 != null) {
            configurarBadgeTipo(holder.binding.tipo1, tipo1)
        }

        if (tipo2 != null) {
            holder.binding.tipo2.visibility = View.VISIBLE
            configurarBadgeTipo(holder.binding.tipo2, tipo2)
        } else {
            holder.binding.tipo2.visibility = View.GONE
        }

        holder.itemView.setOnClickListener { v ->
            viewModel.seleccionarPokemon(pokemon)
            findNavController(v).navigate(R.id.detallesPokemonFragment)
        }

        cambiarIconoFAV(pokemon, holder)
        holder.binding.iconFavorite.setOnClickListener { cambiarATTFav(pokemon, holder) }
    }

    fun getPokemonEn(position: Int): Pokemon = pokemons[position]

    fun establecerLista(pokemons: List<Pokemon>?) {
        if (pokemons != null) this.pokemons = pokemons
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pokemons.size

    private fun configurarBadgeTipo(textView: TextView, tipo: PokemonType) {
        textView.text = tipo.nombre.uppercase()
        textView.setBackgroundResource(R.drawable.badge_tipos)
        textView.background.setTint(ContextCompat.getColor(textView.context, tipo.colorRes()))
    }

    private fun cambiarATTFav(pokemon: Pokemon, holder: PokemonViewHolder) {
        pokemon.favorito = !pokemon.favorito
        cambiarIconoFAV(pokemon, holder)
        viewModel.actualizarPokemonVIEW(pokemon)
    }

    private fun cambiarIconoFAV(pokemon: Pokemon, holder: PokemonViewHolder) {
        if (pokemon.favorito) {
            holder.binding.iconFavorite.setImageResource(R.drawable.estrella_rellena)
        } else {
            holder.binding.iconFavorite.setImageResource(R.drawable.estrella_vacia)
        }
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewholderPokemonBinding = ViewholderPokemonBinding.bind(itemView)
    }
}
