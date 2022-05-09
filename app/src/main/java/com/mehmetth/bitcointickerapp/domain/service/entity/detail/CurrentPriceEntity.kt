package com.mehmetth.bitcointickerapp.domain.service.entity.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentPriceEntity(
    var usd: String?
): Parcelable
