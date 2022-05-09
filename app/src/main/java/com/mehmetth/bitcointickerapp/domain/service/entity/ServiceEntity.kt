package com.mehmetth.bitcointickerapp.domain.service.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.math.BigInteger

@Entity
@Parcelize
data class ServiceEntity(
    @PrimaryKey
    var id: String,
    var symbol: String?,
    var name: String?,
    var image: String?,
    var current_price: String?,
    var market_cap: String?,
    var market_cap_rank: Int?,
    var fully_diluted_valuation: String?,
    var total_volume: String?,
    var high_24h: String?,
    var low_24h: String?,
    var price_change_24h: String?,
    var price_change_percentage_24h: String?,
    var market_cap_change_24h: String?,
    var market_cap_change_percentage_24h: String?,
    var circulating_supply: String?,
    var total_supply: String?,
    var max_supply: String?,
    var ath: String?,
    var ath_change_percentage: String?,
    var ath_date: String?,
    var atl: String?,
    var atl_change_percentage: String?,
    var atl_date: String?,
    var last_updated: String?
): Parcelable