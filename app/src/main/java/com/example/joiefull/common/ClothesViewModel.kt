package com.example.joiefull.common

import GetClothesUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.presentation.bags.BagsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ClothesViewModel (
    private val getClothesUseCase: GetClothesUseCase
) : ViewModel() {

    private val _clothesState = mutableStateOf<List<Clothes>>(emptyList())
    val clothesState: State<List<Clothes>> = _clothesState


    init {
        loadClothes()
    }

    private fun loadClothes() {
        getClothesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _clothesState.value = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    // Handle the error
                }
                is Resource.Loading -> {
                    // Show loading state
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getClothesById(id: Int): Clothes? {
        return _clothesState.value.find { it.id == id }
    }
}
