package com.mehmetth.bitcointickerapp.utils

import android.content.Context
import android.util.Log
import android.view.View
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity

class FirebaseUtils {
    companion object{
        fun addFireStore(context: Context,view: View, coindata: ServiceEntity){
            val coin = hashMapOf(
                "id" to coindata.id,
                "symbol" to coindata.symbol,
                "name" to coindata.name,
                "image" to coindata.image,
                "current_price" to coindata.current_price,
                "market_cap" to coindata.market_cap,
                "market_cap_rank" to coindata.market_cap_rank,
                "fully_diluted_valuation" to coindata.fully_diluted_valuation,
                "total_volume" to coindata.total_volume,
                "high_24h" to coindata.high_24h,
                "low_24h" to coindata.low_24h,
                "price_change_24h" to coindata.price_change_24h,
                "price_change_percentage_24h" to coindata.price_change_percentage_24h,
                "market_cap_change_24h" to coindata.market_cap_change_24h,
                "market_cap_change_percentage_24h" to coindata.market_cap_change_percentage_24h,
                "circulating_supply" to coindata.circulating_supply,
                "total_supply" to coindata.total_supply,
                "max_supply" to coindata.max_supply,
                "ath" to coindata.ath,
                "ath_change_percentage" to coindata.ath_change_percentage,
                "ath_date" to coindata.ath_date,
                "atl" to coindata.atl,
                "atl_change_percentage" to coindata.atl_change_percentage,
                "atl_date" to coindata.atl_date,
                "last_updated" to coindata.last_updated
            )

            Constant.FIREBASEFIRESTOREDB.collection(Constant.FAVORITECOINS)
                .add(coin)
                .addOnSuccessListener { documentReference ->
                    AndroidUtils.showSnackBar(view,context.getString(R.string.saved_successfully))
                }
                .addOnFailureListener { e ->
                    AndroidUtils.showSnackBar(view,context.getString(R.string.unsuccessfully_saved))
                }
        }

        fun readFavoriteCoinFireStore(context: Context, view: View,callback: (HashMap<String,ServiceEntity>) -> Unit){
            val hashMap:HashMap<String,ServiceEntity> = HashMap<String,ServiceEntity>()

            Constant.FIREBASEFIRESTOREDB.collection(Constant.FAVORITECOINS)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        hashMap.put(document.id,ServiceEntity(document["id"].toString(),
                            document["symbol"].toString(),
                            document["name"].toString(),
                            document["image"].toString(),
                            document["current_price"].toString(),
                            document["market_cap"].toString(),
                            document["market_cap_rank"].toString().toInt(),
                            document["fully_diluted_valuation"].toString(),
                            document["total_volume"].toString(),
                            document["high_24h"].toString(),
                            document["low_24h"].toString(),
                            document["price_change_24h"].toString(),
                            document["price_change_percentage_24h"].toString(),
                            document["market_cap_change_24h"].toString(),
                            document["market_cap_change_percentage_24h"].toString(),
                            document["circulating_supply"].toString(),
                            document["total_supply"].toString(),
                            document["max_supply"].toString(),
                            document["ath"].toString(),
                            document["ath_change_percentage"].toString(),
                            document["ath_date"].toString(),
                            document["atl"].toString(),
                            document["atl_change_percentage"].toString(),
                            document["atl_date"].toString(),
                            document["last_updated"].toString()))
                    }
                    callback(hashMap)
                }
                .addOnFailureListener { exception ->
                    AndroidUtils.showSnackBar(view,"${context.getString(R.string.error)}: $exception")
                    callback(hashMap)
                }
        }

        fun deleteFavoriteCoinFireStore(context: Context, view: View, documentId: String){
            Constant.FIREBASEFIRESTOREDB.collection(Constant.FAVORITECOINS).document(documentId)
                .delete()
                .addOnSuccessListener{
                    AndroidUtils.showSnackBar(view,context.getString(R.string.successfully_deleted))
                }
                .addOnSuccessListener{e->
                }
        }
    }
}