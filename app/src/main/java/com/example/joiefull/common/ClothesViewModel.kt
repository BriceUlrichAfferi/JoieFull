package com.example.joiefull.common

import GetClothesUseCase
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.features.domain.model.Clothes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ClothesViewModel(
    private val getClothesUseCase: GetClothesUseCase
) : ViewModel() {

    private val _clothesState = MutableStateFlow<Clothes?>(null)
    val clothesState: StateFlow<Clothes?> = _clothesState

    private val _currentClotheId = mutableStateOf<Int?>(null)

    fun loadClothes(clotheId: Int) {
        _currentClotheId.value = clotheId
        getClothesUseCase(clotheId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _clothesState.value = result.data
                }
                is Resource.Error -> {
                    // Handle error
                    _clothesState.value = null
                }
                is Resource.Loading -> {
                    // Optionally handle loading state
                }
            }
        }.launchIn(viewModelScope)
    }
}