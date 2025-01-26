package com.example.joiefull.presentation.bags

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.common.ClothesState
import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.use_case.GetBagsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BagsViewModel (private val getBagsUseCase: GetBagsUseCase) : ViewModel() {

    private val _bagsState = mutableStateOf(ClothesState())
    val bagsState: State<ClothesState> = _bagsState


    init {
        getBags()
    }

    private fun getBags() {
        getBagsUseCase().onEach { result ->
            when (result){
                is  Resource.Success -> {
                    _bagsState.value = ClothesState(clothes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _bagsState.value = ClothesState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _bagsState.value = ClothesState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}