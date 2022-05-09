package com.mehmetth.bitcointickerapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetth.bitcointickerapp.domain.common.BaseResult
import com.mehmetth.bitcointickerapp.domain.db.CoinDao
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.domain.service.usecase.ServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(private val serviceUseCase: ServiceUseCase,
                                           private val coinDao: CoinDao) : ViewModel(){

    fun getCoinDetail(coinInfo: String): List<ServiceEntity>{
        return coinDao.getCoinDetail(coinInfo)
    }

    private val state = MutableStateFlow<ServicesState>(ServicesState.Init)
    val mState: StateFlow<ServicesState> get() = state

    private val coins = MutableStateFlow<List<ServiceEntity>>(mutableListOf())
    val mCoins: StateFlow<List<ServiceEntity>> get() = coins

    private fun isLoading(value : Boolean){
        state.value = ServicesState.IsLoading(value)
    }
    private fun isError(value : Boolean){
        state.value = ServicesState.IsError(value)
    }
    private fun isErrorMessage(value : String){
        state.value = ServicesState.IsErrorMessage(value)
    }

    fun fetchService(){
        viewModelScope.launch {
            serviceUseCase.execute()
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
                            coins.value = result.data
                            result.data.forEach { item ->
                                coinDao.insert(item)
                            }
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

sealed class ServicesState {
    object Init : ServicesState()
    data class IsLoading(val isLoading: Boolean) : ServicesState()
    data class IsError(val isError: Boolean) : ServicesState()
    data class IsErrorMessage(val isErrorValue: String) : ServicesState()
}