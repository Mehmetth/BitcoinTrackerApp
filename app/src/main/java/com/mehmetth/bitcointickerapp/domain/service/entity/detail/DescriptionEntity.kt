package com.mehmetth.bitcointickerapp.domain.service.entity.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DescriptionEntity(
    var en: String?
): Parcelable
