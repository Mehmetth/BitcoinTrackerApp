package com.mehmetth.bitcointickerapp.data.service.remote.api

import com.mehmetth.bitcointickerapp.data.service.remote.dto.ServiceResponse
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.ServiceDetailResponse
import com.mehmetth.bitcointickerapp.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ServiceApi{
    @GET("markets")
    suspend fun getServices(
        @Query("vs_currency") vs_currency:String = Constant.VSCURRENCY) : Response<List<ServiceResponse>>

    @GET("{coinId}")
    suspend fun getServiceDetail(
        @Path("coinId") coinId: String): Response<ServiceDetailResponse>
}