package com.example.joelsanchez_pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.joelsanchez_pokeapi.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {

    private var _binding : FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPokemonBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPrueba.setOnClickListener {
            // Navegación CORRECTA usando Navigation Component
            findNavController().navigate(R.id.detallesPokemonFragment)
        }

        binding.btnPruebaF.setOnClickListener {
            // Navegación CORRECTA usando Navigation Component
            findNavController().navigate(R.id.favoritesPokemonFragment)
        }

    }

    //------------------------
    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}