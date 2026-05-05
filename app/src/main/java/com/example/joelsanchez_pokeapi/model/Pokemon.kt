package com.example.joelsanchez_pokeapi.model

import java.io.Serializable

data class Pokemon (

    val nombre: String?,
    val imagen: Int,
    val descripcion: String?,
    val tipo1: String?,
    val tipo2: String?,
    var favorito: Boolean = false

) : Serializable {

    fun camAtributoFavorito (favorito : Boolean) {

        this.favorito = favorito

    }

}