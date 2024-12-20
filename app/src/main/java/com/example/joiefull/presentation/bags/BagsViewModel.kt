package com.example.joiefull.presentation.bags

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull.common.Constants
import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.use_case.GetBagsUseCase
import com.example.joiefull.presentation.bags.BagsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class BagsViewModel (private val getBagsUseCase: GetBagsUseCase) : ViewModel() {

    private val _bagsState = mutableStateOf(BagsState())
    val bagsState: State<BagsState> = _bagsState


    init {
        getBags(Constants.PARAM_BAGS_CATEGORY)
    }


    private fun getBags(bagsCategory: String) {
        getBagsUseCase(bagsCategory).onEach { result ->
            when (result){
                is  Resource.Success -> {
                    _bagsState.value = BagsState(bags = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _bagsState.value = BagsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _bagsState.value = BagsState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}