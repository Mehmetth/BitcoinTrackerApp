package com.mehmetth.bitcointickerapp.domain.service.entity.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LowEntity(
    var usd: String?
): Parcelable
