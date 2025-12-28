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
import com.example.joelsanchez_pokeapi.databinding.FragmentDetallesPokemonBinding
import com.example.joelsanchez_pokeapi.modelview.PokemonViewModel

class DetallesPokemonFragment : Fragment() {

    private var _binding : FragmentDetallesPokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetallesPokemonBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        viewModel.pokemonSeleccionado.observe(viewLifecycleOwner) { poke ->

            if (poke != null) {

                binding.imagen.setImageResource(poke.imagen)
                binding.nombre.text = poke.nombre
                binding.descripcion.text = poke.descripcion

                // Configuramos la BG del primer tipo
                configurarTipo(binding.tipo1, poke.tipo1)

                // Y para el segundo, primero vamos a comprobar que tenga dos tipos, si no, que no se muestre
                if (!poke.tipo2.isNullOrEmpty()) {

                    binding.tipo2.visibility = View.VISIBLE
                    configurarTipo(binding.tipo2, poke.tipo2)

                } else {
                    binding.tipo2.visibility = View.GONE
                }

            } else {

                Toast.makeText(requireContext(), "No se pudo cargar el detalle del animal", Toast.LENGTH_SHORT).show()

            }

        }

    }

    fun colorPorTipo(tipo: String?): Int {

        return when (tipo?.lowercase()) {

            "eléctrico" -> R.color.tipo_electrico
            "fuego" -> R.color.tipo_fuego
            "agua" -> R.color.tipo_agua
            "planta" -> R.color.tipo_planta
            "volador" -> R.color.tipo_volador
            else -> R.color.tipo_normal

        }

    }

    fun configurarTipo(textView: TextView, tipo: String?) {

        textView.text = tipo?.uppercase()
        textView.setBackgroundResource(R.drawable.badge_tipos)

        textView.background.setTint(

            ContextCompat.getColor(textView.context, colorPorTipo(tipo))

        )

    }

    //------------------------
    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}