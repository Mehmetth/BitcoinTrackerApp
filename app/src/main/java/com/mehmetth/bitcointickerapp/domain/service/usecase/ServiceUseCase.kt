package com.mehmetth.bitcointickerapp.domain.service.usecase

import com.mehmetth.bitcointickerapp.data.service.remote.dto.ServiceResponse
import com.mehmetth.bitcointickerapp.domain.common.BaseResult
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.domain.service.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceUseCase @Inject constructor(private val serviceRepository: ServiceRepository) {
    suspend fun execute(): Flow<BaseResult<List<ServiceEntity>, ServiceResponse>> {
        return serviceRepository.getService()
    }
}