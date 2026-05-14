package com.example.joelsanchez_pokeapi.model

import com.example.joelsanchez_pokeapi.R

class PokemonType {

    var type: TypeDetails? = null

    class TypeDetails {
        var name: String? = null

        constructor()
        constructor(nombre: String) {
            name = nombre
        }
    }

    constructor()

    // Constructor con nombre → lo usamos nosotros al cargar desde Room
    constructor(typeName: String) {
        type = TypeDetails(typeName)
    }

    val nombreEs: String get() = when (type?.name?.lowercase()) {
        "fire"     -> "Fuego"
        "water"    -> "Agua"
        "grass"    -> "Planta"
        "electric" -> "Eléctrico"
        "poison"   -> "Veneno"
        "flying"   -> "Volador"
        "bug"      -> "Bicho"
        "rock"     -> "Roca"
        "ghost"    -> "Fantasma"
        "dragon"   -> "Dragón"
        "psychic"  -> "Psíquico"
        "fighting" -> "Lucha"
        "ground"   -> "Tierra"
        "ice"      -> "Hielo"
        "dark"     -> "Siniestro"
        "steel"    -> "Acero"
        "fairy"    -> "Hada"
        "normal"   -> "Normal"
        else       -> type?.name?.replaceFirstChar { it.uppercase() } ?: ""
    }

    fun colorRes(): Int = when (type?.name?.lowercase()) {
        "fire"     -> R.color.tipo_fuego
        "water"    -> R.color.tipo_agua
        "grass"    -> R.color.tipo_planta
        "electric" -> R.color.tipo_electrico
        "flying"   -> R.color.tipo_volador
        "poison"   -> R.color.tipo_veneno
        "bug"      -> R.color.tipo_bicho
        "rock"     -> R.color.tipo_roca
        "ghost"    -> R.color.tipo_fantasma
        "dragon"   -> R.color.tipo_dragon
        "psychic"  -> R.color.tipo_psiquico
        "fighting" -> R.color.tipo_lucha
        "ground"   -> R.color.tipo_tierra
        "ice"      -> R.color.tipo_hielo
        "dark"     -> R.color.tipo_siniestro
        "steel"    -> R.color.tipo_acero
        "fairy"    -> R.color.tipo_hada
        else       -> R.color.tipo_normal
    }
}
