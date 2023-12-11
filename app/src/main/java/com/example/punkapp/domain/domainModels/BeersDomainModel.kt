package com.example.punkapp.domain.domainModels


data class BeersDomainModel(
    val abv: Double?,
    val attenuationLevel: Double?,
    val boilVolume: VolumeDomainModel?,
    val brewersTips: String?,
    val contributedBy: String?,
    val description: String?,
    val ebc: Int?,
    val firstBrewed: String?,
    val foodPairing: List<String>?,
    val ibu: Double?,
    val id: Int?,
    val imageUrl: String?,
    val name: String?,
    val ph: Double?,
    val srm: Double?,
    val tagline: String?,
    val targetFg: Int?,
    val targetOg: Double?,
    val volume: VolumeDomainModel?
)
