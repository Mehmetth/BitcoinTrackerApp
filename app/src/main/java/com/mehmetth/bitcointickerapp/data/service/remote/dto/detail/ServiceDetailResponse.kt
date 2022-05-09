package com.mehmetth.bitcointickerapp.data.service.remote.dto.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceDetailResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("symbol")
    var symbol: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("block_time_in_minutes")
    var block_time_in_minutes: Int?,
    @SerializedName("hashing_algorithm")
    var hashing_algorithm: String?,
    @SerializedName("image")
    var image: ImageResponse?,
    @SerializedName("description")
    var description: DescriptionResponse?,
    @SerializedName("market_data")
    var market_data: MarketDataResponse?,
): Parcelable
