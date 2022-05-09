package com.mehmetth.bitcointickerapp.data.service.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.math.BigInteger

@Parcelize
data class ServiceResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("symbol")
    var symbol: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("current_price")
    var current_price: Double?,
    @SerializedName("market_cap")
    var market_cap: BigInteger?,
    @SerializedName("market_cap_rank")
    var market_cap_rank: Int?,
    @SerializedName("fully_diluted_valuation")
    var fully_diluted_valuation: BigInteger?,
    @SerializedName("total_volume")
    var total_volume: Double?,
    @SerializedName("high_24h")
    var high_24h: Double?,
    @SerializedName("low_24h")
    var low_24h: Double?,
    @SerializedName("price_change_24h")
    var price_change_24h: Double?,
    @SerializedName("price_change_percentage_24h")
    var price_change_percentage_24h: Double?,
    @SerializedName("market_cap_change_24h")
    var market_cap_change_24h: Double?,
    @SerializedName("market_cap_change_percentage_24h")
    var market_cap_change_percentage_24h: Double?,
    @SerializedName("circulating_supply")
    var circulating_supply: Double?,
    @SerializedName("total_supply")
    var total_supply: Double?,
    @SerializedName("max_supply")
    var max_supply: Double?,
    @SerializedName("ath")
    var ath: Double?,
    @SerializedName("ath_change_percentage")
    var ath_change_percentage: Double?,
    @SerializedName("ath_date")
    var ath_date: String?,
    @SerializedName("atl")
    var atl: Double?,
    @SerializedName("atl_change_percentage")
    var atl_change_percentage: Double?,
    @SerializedName("atl_date")
    var atl_date: String?,
    @SerializedName("last_updated")
    var last_updated: String?
): Parcelable
