package com.example.joiefull.presentation.bottoms

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.common.Constants
import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.use_case.GetBottomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class BottomsViewModel (
    private val getBottomsUseCase: GetBottomsUseCase
) : ViewModel() {

    private val _bottomsState = mutableStateOf(BottomsState())
    val bottomsState: State<BottomsState> = _bottomsState


    init {
        getBottoms(Constants.PARAM_BOTTOM_CATEGORY)
    }


    private fun getBottoms(bottomsCategory: String) {
        getBottomsUseCase(bottomsCategory).onEach { result ->
            when (result){
                is  Resource.Success -> {
                    _bottomsState.value = BottomsState(bottoms = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _bottomsState.value = BottomsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _bottomsState.value = BottomsState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}