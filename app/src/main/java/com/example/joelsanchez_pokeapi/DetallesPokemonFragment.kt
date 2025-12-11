package com.example.joelsanchez_pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joelsanchez_pokeapi.databinding.FragmentDetallesPokemonBinding
import com.example.joelsanchez_pokeapi.databinding.FragmentPokemonBinding

class DetallesPokemonFragment : Fragment() {

    private var _binding : FragmentDetallesPokemonBinding? = null
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

        _binding = FragmentDetallesPokemonBinding.inflate(layoutInflater)
        return binding.root

    }

    //------------------------
    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}