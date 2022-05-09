package com.mehmetth.bitcointickerapp.domain.service.usecase

import com.mehmetth.bitcointickerapp.data.service.remote.dto.ServiceResponse
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.ServiceDetailResponse
import com.mehmetth.bitcointickerapp.domain.common.BaseResult
import com.mehmetth.bitcointickerapp.domain.service.ServiceRepository
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.domain.service.entity.detail.ServiceDetailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceDetailUseCase @Inject constructor(private val serviceRepository: ServiceRepository) {
    suspend fun execute(coinId: String): Flow<BaseResult<ServiceDetailEntity, ServiceDetailResponse>> {
        return serviceRepository.getServiceDetail(coinId)
    }
}