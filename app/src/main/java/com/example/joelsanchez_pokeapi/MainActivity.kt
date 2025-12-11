package com.example.joelsanchez_pokeapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.joelsanchez_pokeapi.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private var _binding : MainActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()

        // Evita memory leaks (fugas de memoria)
        // Va limpiando memoria reservada que no se libera
        // PROBLEMA -> Si no se borran, pueden agotar memoria en exceso
        _binding = null

    }

}