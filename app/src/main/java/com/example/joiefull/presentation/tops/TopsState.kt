package com.example.joiefull.presentation.tops

import com.example.joiefull.features.domain.model.Clothes

data class TopsState(

    val isLoading : Boolean = false,
    val tops : List<Clothes> = emptyList(),
    val error : String= ""

)
