package com.example.punkapp.domain.dto

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: String
)