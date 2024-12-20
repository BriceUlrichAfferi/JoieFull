package com.example.joiefull.presentation.bags

import com.example.joiefull.features.domain.model.Clothes

data class BagsState(

    val isLoading : Boolean = false,
    val bags : List<Clothes> = emptyList(),
    val error : String= ""
)
