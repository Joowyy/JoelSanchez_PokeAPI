package com.example.joelsanchez_pokeapi.model

data class Pokemon (

    val id: Int,
    val nombre: String?,
    val imagen: Int,
    val descripcion: String?,
    val tipo1: String?,
    val tipo2: String?,
    var favorito: Boolean = false

)