package com.example.joelsanchez_pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.joelsanchez_pokeapi.adapter.PokemonAdapter
import com.example.joelsanchez_pokeapi.databinding.FragmentPokemonBinding
import com.example.joelsanchez_pokeapi.modelview.PokemonViewModel
import com.example.joelsanchez_pokeapi.repository.PokemonRepository

class PokemonFragment : Fragment() {

    private var _binding : FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : PokemonAdapter
    private lateinit var repository : PokemonRepository
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

        _binding = FragmentPokemonBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = PokemonRepository()
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
        adapter = PokemonAdapter(requireContext(), mutableListOf(), viewModel)

        binding.recyclerView.apply {

            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@PokemonFragment.adapter

        }

        viewModel.pokemons.observe(viewLifecycleOwner) { lista ->

            adapter.establecerLista(lista)

        }

        viewModel.obtenerPokemons()

//        binding.btnPrueba.setOnClickListener {
//            // Navegación CORRECTA usando Navigation Component
//            findNavController().navigate(R.id.detallesPokemonFragment)
//        }
//
//        binding.btnPruebaF.setOnClickListener {
//            // Navegación CORRECTA usando Navigation Component
//            findNavController().navigate(R.id.favoritesPokemonFragment)
//        }

    }

    //------------------------
    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}