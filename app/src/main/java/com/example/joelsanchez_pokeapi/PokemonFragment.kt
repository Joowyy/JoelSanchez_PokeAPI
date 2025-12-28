package com.example.joelsanchez_pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

        // Inflamos el layout
        _binding = FragmentPokemonBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Le damos el contenido/valor necesario a cada variable
        repository = PokemonRepository()
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
        adapter = PokemonAdapter(requireContext(), mutableListOf(), viewModel)

        // Aplicamos el recyclerView indicando tipo de layout, el fragment y las columnas
        binding.recyclerView.apply {

            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@PokemonFragment.adapter

        }

        // Observamos los cambios de la lista
        viewModel.pokemons.observe(viewLifecycleOwner) { lista ->

            adapter.establecerLista(lista)

        }

        viewModel.obtenerPokemons()

        eventoEliminarPoke(view)

    }

    private fun eventoEliminarPoke(view: View) {

        val callback = object : ItemTouchHelper.SimpleCallback(
            0, // No movemos elementos
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT // Deslizar izquierda/derecha
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // No necesitamos movimiento (solo eliminar)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // 1. Posición del elemento deslizado
                val position = viewHolder.bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION) {

                    viewModel.eliminarPokemonVIEW(position)

                }

            }

        }

        // Asociar callback al RecyclerView
        ItemTouchHelper(callback).attachToRecyclerView(binding.recyclerView)
    }

    //------------------------
    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

}