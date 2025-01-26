package com.example.joiefull.common

import com.example.joiefull.features.domain.model.Clothes

data class ClothesState(

    val isLoading : Boolean = false,
    val clothes : List<Clothes> = emptyList(),
    val error : String= ""
)
