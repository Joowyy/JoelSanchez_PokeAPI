package com.example.joelsanchez_pokeapi.model

import java.io.Serializable
import java.util.Objects

class Pokemon (

    val nombre: String?,
    val imagen: Int,
    val descripcion: String?,
    val tipo: String?,
    var favorito: Boolean = false

) : Serializable {

    override fun hashCode(): Int {

        return Objects.hash(nombre, imagen, descripcion, tipo, favorito)

    }

    fun camAtributoFavorito (favorito : Boolean) {

        this.favorito = favorito

    }

}