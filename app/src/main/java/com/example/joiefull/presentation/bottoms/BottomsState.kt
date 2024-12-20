package com.example.joiefull.presentation.bottoms

import com.example.joiefull.features.domain.model.Clothes

data class BottomsState(

    val isLoading : Boolean = false,
    val bottoms : List<Clothes> = emptyList(),
    val error : String= ""
)
