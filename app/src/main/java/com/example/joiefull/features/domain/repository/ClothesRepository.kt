package com.example.joiefull.features.domain.repository

import com.example.joiefull.features.domain.model.Clothes

interface ClothesRepository {


    //These are what the repository offer
    suspend fun getClothes(): List<Clothes>
    suspend fun getClothesById(id:Int): Clothes
    suspend fun getClothesByCategory(category: String): List<Clothes>
}