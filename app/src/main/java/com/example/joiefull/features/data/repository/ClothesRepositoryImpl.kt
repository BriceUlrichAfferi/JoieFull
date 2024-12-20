package com.example.joiefull.features.data.repository

import com.example.joiefull.features.data.remote.ClothesApiService
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import javax.inject.Inject

class ClothesRepositoryImpl(private val apiService: ClothesApiService) :
    ClothesRepository {


    // Fetch all clothes from the API
    override suspend fun getClothes(): List<Clothes> {
        return apiService.getClothes()
    }

    // Fetch a specific clothes item by ID
    override suspend fun getClothesById(id: Int): Clothes {
        val clothesList = apiService.getClothes()
        return clothesList.find { it.id == id }
            ?: throw NoSuchElementException("No clothes item found with ID: $id")
    }

    override suspend fun getClothesByCategory(category: String): List<Clothes> {
        return apiService.getClothes().filter { it.category.equals(category, ignoreCase = true) }
    }

}