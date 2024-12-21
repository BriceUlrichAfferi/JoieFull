package com.example.joiefull.features.data.repository

import com.example.joiefull.features.data.remote.ClothesApiService
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import javax.inject.Inject

class ClothesRepositoryImpl(private val apiService: ClothesApiService) :
    ClothesRepository {


    private var cachedClothesList: List<Clothes>? = null

    // Fetch all clothes from the API
    override suspend fun getClothes(): List<Clothes> {
        return cachedClothesList ?: apiService.getClothes().also {
            cachedClothesList = it // Cache the list
        }
    }
    // Fetch all clothes from the API


    // Fetch a specific clothes item by ID
    override suspend fun getClothesById(id: Int): Clothes {
        // First, check if we already have the cached clothes list
        val clothesList = cachedClothesList ?: run {
            // If not, fetch from the API and cache it
            apiService.getClothes().also {
                cachedClothesList = it
            }
        }

        // Now search the list for the item with the provided ID
        return clothesList.find { it.id == id }
            ?: throw NoSuchElementException("No clothes item found with ID: $id")
    }


    override suspend fun getClothesByCategory(category: String): List<Clothes> {
        return apiService.getClothes().filter { it.category.equals(category, ignoreCase = true) }
    }

}