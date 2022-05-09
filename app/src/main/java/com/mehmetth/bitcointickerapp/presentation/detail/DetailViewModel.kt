package com.mehmetth.bitcointickerapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetth.bitcointickerapp.domain.common.BaseResult
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.domain.service.entity.detail.ServiceDetailEntity
import com.mehmetth.bitcointickerapp.domain.service.usecase.ServiceDetailUseCase
import com.mehmetth.bitcointickerapp.domain.service.usecase.ServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val serviceDetailUseCase: ServiceDetailUseCase) : ViewModel(){

    private val state = MutableStateFlow<ServiceDetailState>(ServiceDetailState.Init)
    val mState: StateFlow<ServiceDetailState> get() = state

    private val coinDetail = MutableStateFlow<ServiceDetailEntity?>(null)
    val mCoinDetail: StateFlow<ServiceDetailEntity?> get() = coinDetail

    private fun isLoading(value : Boolean){
        state.value = ServiceDetailState.IsLoading(value)
    }
    private fun isError(value : Boolean){
        state.value = ServiceDetailState.IsError(value)
    }
    private fun isErrorMessage(value : String){
        state.value = ServiceDetailState.IsErrorMessage(value)
    }

    fun fetchServiceDetail(coinId: String){
        viewModelScope.launch {
            serviceDetailUseCase.execute(coinId)
                .onStart {
                    isLoading(true)
                    isError(false)
                }
                .catch { exception ->
                    isError(true)
                    isErrorMessage(exception.message.toString())
                }
                .collect { result ->
                    isLoading(false)
                    when(result){
                        is BaseResult.Success -> {
                            coinDetail.value = result.data
                            isError(false)
                            isLoading(false)
                        }
                        is BaseResult.Error -> {
                            isError(true)
                            isLoading(false)
                            isErrorMessage(result.toString())
                        }
                    }

                }
        }
    }
}

sealed class ServiceDetailState {
    object Init : ServiceDetailState()
    data class IsLoading(val isLoading: Boolean) : ServiceDetailState()
    data class IsError(val isError: Boolean) : ServiceDetailState()
    data class IsErrorMessage(val isErrorValue: String) : ServiceDetailState()
}