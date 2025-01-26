package com.example.joiefull.presentation.bottoms

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.common.ClothesState
import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.use_case.GetBottomsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BottomsViewModel (
    private val getBottomsUseCase: GetBottomsUseCase
) : ViewModel() {

    private val _bottomsState = mutableStateOf(ClothesState())
    val bottomsState: State<ClothesState> = _bottomsState


    init {
        getBottoms()
    }

    private fun getBottoms() {
        getBottomsUseCase().onEach { result ->
            when (result){
                is  Resource.Success -> {
                    _bottomsState.value = ClothesState(clothes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _bottomsState.value = ClothesState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _bottomsState.value = ClothesState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}