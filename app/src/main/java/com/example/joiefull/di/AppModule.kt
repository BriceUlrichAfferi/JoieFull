package com.example.joiefull.di

import GetClothesUseCase
import com.example.joiefull.common.ClothesViewModel
import com.example.joiefull.common.Constants
import com.example.joiefull.common.LikesViewModel
import com.example.joiefull.features.data.remote.ClothesApiService
import com.example.joiefull.features.data.repository.ClothesRepositoryImpl
import com.example.joiefull.features.domain.repository.ClothesRepository
import com.example.joiefull.features.domain.use_case.GetBagsUseCase
import com.example.joiefull.features.domain.use_case.GetBottomsUseCase
import com.example.joiefull.features.domain.use_case.GetTopsUseCase
import com.example.joiefull.presentation.bags.BagsViewModel
import com.example.joiefull.presentation.bottoms.BottomsViewModel
import com.example.joiefull.presentation.tops.TopsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // API service
    single<ClothesApiService> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClothesApiService::class.java)
    }

    // Repository
    single<ClothesRepository> { ClothesRepositoryImpl(get()) }

    // Use cases
    single { GetTopsUseCase(get()) }
    single { GetBagsUseCase(get()) }
    single { GetBottomsUseCase(get()) }
    single { GetClothesUseCase(get()) }

    // ViewModels
    viewModel { TopsViewModel(get()) }
    viewModel { BottomsViewModel(get()) }
    viewModel { ClothesViewModel(get()) }
    viewModel { BagsViewModel(get()) }
    viewModel { LikesViewModel(get()) }
}

