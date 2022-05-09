package com.mehmetth.bitcointickerapp.data.service.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mehmetth.bitcointickerapp.data.service.remote.api.ServiceApi
import com.mehmetth.bitcointickerapp.data.service.remote.dto.ServiceResponse
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.ServiceDetailResponse
import com.mehmetth.bitcointickerapp.domain.common.BaseResult
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.domain.service.ServiceRepository
import com.mehmetth.bitcointickerapp.domain.service.entity.detail.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(private val serviceApi: ServiceApi) :
    ServiceRepository {
    override suspend fun getService(): Flow<BaseResult<List<ServiceEntity>, ServiceResponse>> {
        return flow {
            val response = serviceApi.getServices()
            if (response.isSuccessful){
                val body = response.body()!!

                val responseList = mutableListOf<ServiceEntity>()
                body.forEach { item ->
                    responseList.add(
                        ServiceEntity(item.id!!,
                        item.symbol,
                        item.name,
                        item.image,
                        item.current_price.toString(),
                        item.market_cap.toString(),
                        item.market_cap_rank,
                        item.fully_diluted_valuation.toString(),
                        item.total_volume.toString(),
                        item.high_24h.toString(),
                        item.low_24h.toString(),
                        item.price_change_24h.toString(),
                        item.price_change_percentage_24h.toString(),
                        item.market_cap_change_24h.toString(),
                        item.market_cap_change_percentage_24h.toString(),
                        item.circulating_supply.toString(),
                        item.total_supply.toString(),
                        item.max_supply.toString(),
                        item.ath.toString(),
                        item.ath_change_percentage.toString(),
                        item.ath_date,
                        item.atl.toString(),
                        item.atl_change_percentage.toString(),
                        item.atl_date,
                        item.last_updated)
                    )
                }

                emit(BaseResult.Success(responseList))
            }else{
                val type = object : TypeToken<ServiceResponse>(){}.type
                val err = Gson().fromJson<ServiceResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun getServiceDetail(coinId: String): Flow<BaseResult<ServiceDetailEntity, ServiceDetailResponse>> {
        return flow {
            val response = serviceApi.getServiceDetail(coinId)
            if (response.isSuccessful){
                val body = response.body()!!

                emit(BaseResult.Success(
                    ServiceDetailEntity(body.id,body.symbol,body.name,body.block_time_in_minutes,body.hashing_algorithm,
                        ImageEntity(body.image!!.large),
                        DescriptionEntity(body.description!!.en),
                        MarketDataEntity(
                            CurrentPriceEntity(body.market_data!!.current_priceResponse!!.usd),
                            HighEntity(body.market_data!!.high_24h!!.usd),
                            LowEntity(body.market_data!!.low_24h!!.usd)))))
            }else{
                val type = object : TypeToken<ServiceDetailResponse>(){}.type
                val err = Gson().fromJson<ServiceDetailResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

}