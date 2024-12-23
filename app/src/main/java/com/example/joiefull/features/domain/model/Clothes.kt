package com.example.joiefull.features.domain.model

import java.io.Serializable


data class Clothes(
    val id: Int,
    val picture: Pictures, // Use the correct class name here
    val name: String,
    val category: String,
    var likes: Int,
    val price: Double,
    val original_price: Double
) : Serializable



