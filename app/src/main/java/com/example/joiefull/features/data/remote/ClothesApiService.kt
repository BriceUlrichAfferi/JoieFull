package com.example.joiefull.features.data.remote

import com.example.joiefull.features.domain.model.Clothes
import retrofit2.http.GET

interface ClothesApiService {

    @GET("clothes.json") // Full path appended to the base URL
    suspend fun getClothes(): List<Clothes>


}