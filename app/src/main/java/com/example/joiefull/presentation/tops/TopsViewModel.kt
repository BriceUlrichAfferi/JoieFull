package com.example.joiefull.presentation.tops

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.common.Constants
import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.use_case.GetBottomsUseCase
import com.example.joiefull.features.domain.use_case.GetTopsUseCase
import com.example.joiefull.presentation.bottoms.BottomsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TopsViewModel (
    private val getTopsUseCase: GetTopsUseCase
) : ViewModel() {

    private val _topsState = mutableStateOf(TopsState())
    val topsState: State<TopsState> = _topsState


    init {
        getTops(Constants.PARAM_TOPS_CATEGORY)
    }


    private fun getTops(topsCategory: String) {
        getTopsUseCase(topsCategory).onEach { result ->
            when (result){
                is  Resource.Success -> {
                    _topsState.value = TopsState(tops = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _topsState.value = TopsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _topsState.value = TopsState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}