package com.example.joiefull.features.domain.repository

import com.example.joiefull.features.domain.model.Clothes

interface ClothesRepository {

    suspend fun getClothes(): List<Clothes>
    suspend fun getClothesById(id:Int): Clothes
    suspend fun getClothesByCategory(category: String): List<Clothes>
}