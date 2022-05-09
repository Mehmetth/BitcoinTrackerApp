package com.mehmetth.bitcointickerapp.data.service.remote.dto.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarketDataResponse(
    @SerializedName("current_price")
    var current_priceResponse: CurrentPriceResponse?,
    @SerializedName("high_24h")
    var high_24h: HighResponse?,
    @SerializedName("low_24h")
    var low_24h: LowResponse?,
): Parcelable
