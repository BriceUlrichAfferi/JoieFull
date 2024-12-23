package com.example.joiefull.common

import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository

suspend fun getClothesById(clothesRepository: ClothesRepository, id: Int): Clothes? {
    return try {
        clothesRepository.getClothesById(id)
    } catch (e: NoSuchElementException) {
        null
    }
}