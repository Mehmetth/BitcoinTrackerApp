package com.mehmetth.bitcointickerapp.data.service.remote.dto.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentPriceResponse(
    @SerializedName("usd")
    var usd: String?
): Parcelable
