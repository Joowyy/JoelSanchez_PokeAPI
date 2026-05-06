package com.example.joelsanchez_pokeapi.model

import com.example.joelsanchez_pokeapi.R

data class PokemonType(val nombre: String) {

    fun colorRes(): Int = when (nombre.lowercase()) {
        "fuego"     -> R.color.tipo_fuego
        "agua"      -> R.color.tipo_agua
        "planta"    -> R.color.tipo_planta
        "eléctrico" -> R.color.tipo_electrico
        "volador"   -> R.color.tipo_volador
        "veneno"    -> R.color.tipo_veneno
        "bicho"     -> R.color.tipo_bicho
        "roca"      -> R.color.tipo_roca
        "fantasma"  -> R.color.tipo_fantasma
        "dragón"    -> R.color.tipo_dragon
        "psíquico"  -> R.color.tipo_psiquico
        "lucha"     -> R.color.tipo_lucha
        "tierra"    -> R.color.tipo_tierra
        "hielo"     -> R.color.tipo_hielo
        "siniestro" -> R.color.tipo_siniestro
        "acero"     -> R.color.tipo_acero
        "hada"      -> R.color.tipo_hada
        else        -> R.color.tipo_normal
    }
}
