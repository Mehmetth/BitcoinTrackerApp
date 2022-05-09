package com.mehmetth.bitcointickerapp.domain.service.entity.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarketDataEntity(
    var current_priceResponse: CurrentPriceEntity?,
    var high_24h: HighEntity?,
    var low_24h: LowEntity?
): Parcelable
