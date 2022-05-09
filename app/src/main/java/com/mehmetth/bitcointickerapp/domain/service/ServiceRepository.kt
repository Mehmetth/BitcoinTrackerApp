package com.mehmetth.bitcointickerapp.domain.service

import com.mehmetth.bitcointickerapp.data.service.remote.dto.ServiceResponse
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.ServiceDetailResponse
import com.mehmetth.bitcointickerapp.domain.common.BaseResult
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.domain.service.entity.detail.ServiceDetailEntity
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    suspend fun getService() : Flow<BaseResult<List<ServiceEntity>, ServiceResponse>>
    suspend fun getServiceDetail(coinId: String) : Flow<BaseResult<ServiceDetailEntity, ServiceDetailResponse>>
}