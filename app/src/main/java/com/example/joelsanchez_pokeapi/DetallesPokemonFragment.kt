package com.example.joelsanchez_pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.joelsanchez_pokeapi.databinding.FragmentDetallesPokemonBinding
import com.example.joelsanchez_pokeapi.model.PokemonType
import com.example.joelsanchez_pokeapi.modelview.PokemonViewModel

class DetallesPokemonFragment : Fragment() {

    private var _binding: FragmentDetallesPokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetallesPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        viewModel.pokemonSeleccionado.observe(viewLifecycleOwner) { poke ->

            if (poke != null) {

                Glide.with(this).load(poke.imagen).into(binding.imagen)
                binding.nombre.text = poke.name
                binding.tvAltura.text = "${poke.altura}m"
                binding.tvPeso.text = "${poke.peso}kg"

                val tipo1 = poke.types.getOrNull(0)
                val tipo2 = poke.types.getOrNull(1)

                if (tipo1 != null) {
                    configurarTipo(binding.tipo1, tipo1)
                }

                if (tipo2 != null) {
                    binding.tipo2.visibility = View.VISIBLE
                    configurarTipo(binding.tipo2, tipo2)
                } else {
                    binding.tipo2.visibility = View.GONE
                }

            } else {
                Toast.makeText(requireContext(), "No se pudo cargar el detalle del Pokémon", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun configurarTipo(textView: TextView, tipo: PokemonType) {
        textView.text = tipo.nombreEs.uppercase()
        textView.setBackgroundResource(R.drawable.badge_tipos)
        textView.background.setTint(ContextCompat.getColor(textView.context, tipo.colorRes()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
