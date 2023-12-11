package com.example.punkapp.domain.mappers

import com.example.punkapp.domain.dto.BeersDto
import com.example.punkapp.domain.domainModels.BeersDomainModel
import com.example.punkapp.domain.domainModels.VolumeDomainModel

fun toBeersDomainModel(beersDtoItem: BeersDto): BeersDomainModel {
    val domainModel = with(beersDtoItem) {
        val boilVolumeDomainModel = VolumeDomainModel(
            unit = this.boil_volume?.unit,
            value = this.boil_volume?.value
        )
        val volumeDomainModel = VolumeDomainModel(
            unit = this.volume?.unit,
            value = this.volume?.value
        )

        return@with BeersDomainModel(
            abv = this.abv,
            attenuationLevel = this.attenuation_level,
            boilVolume = boilVolumeDomainModel,
            brewersTips = this.brewers_tips,
            contributedBy = this.contributed_by,
            description = this.description,
            ebc = this.ebc,
            firstBrewed = this.first_brewed,
            foodPairing = this.food_pairing,
            ibu = this.ibu,
            id = this.id,
            imageUrl = this.image_url,
            name = this.name,
            ph = this.ph,
            srm = this.srm,
            tagline = this.tagline,
            targetFg = this.target_fg,
            targetOg = this.target_og,
            volume = volumeDomainModel
        )
    }
    return domainModel
}