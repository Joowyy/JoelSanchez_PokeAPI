package com.example.joelsanchez_pokeapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.joelsanchez_pokeapi.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    // Separamos el binding, el _binding se usa de "backing property"
    private var _binding : MainActivityBinding? = null
    private val binding get() = _binding!!

    // Controlador y configuracion de la barra
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    //------------------------
    override fun onDestroy() {
        super.onDestroy()

        // Evita memory leaks (fugas de memoria)
        // Va limpiando memoria reservada que no se libera
        // PROBLEMA -> Si no se borran, pueden agotar memoria en exceso
        _binding = null

    }

}