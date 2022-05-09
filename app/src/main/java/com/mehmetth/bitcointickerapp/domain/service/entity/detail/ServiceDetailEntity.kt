package com.mehmetth.bitcointickerapp.domain.service.entity.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.DescriptionResponse
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.ImageResponse
import com.mehmetth.bitcointickerapp.data.service.remote.dto.detail.MarketDataResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceDetailEntity(
    var id: String?,
    var symbol: String?,
    var name: String?,
    var block_time_in_minutes: Int?,
    var hashing_algorithm: String?,
    var image: ImageEntity?,
    var description: DescriptionEntity?,
    var market_data: MarketDataEntity?,
): Parcelable
