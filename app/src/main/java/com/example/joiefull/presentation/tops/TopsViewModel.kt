package com.example.joiefull.presentation.tops

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.common.ClothesState
import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.use_case.GetTopsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TopsViewModel (
    private val getTopsUseCase: GetTopsUseCase
) : ViewModel() {

    private val _topsState = mutableStateOf(ClothesState())
    val topsState: State<ClothesState> = _topsState

    init {
        getTops()
    }

    private fun getTops() {
        getTopsUseCase().onEach { result ->
            when (result){
                is  Resource.Success -> {
                    _topsState.value = ClothesState(clothes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _topsState.value = ClothesState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _topsState.value = ClothesState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}