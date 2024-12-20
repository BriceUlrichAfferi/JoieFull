package com.example.joiefull.features.domain.model



data class Clothes(
    val id: Int,
    val picture: Pictures, // Use the correct class name here
    val name: String,
    val category: String,
    val likes: Int,
    val price: Double,
    val original_price: Double
)



