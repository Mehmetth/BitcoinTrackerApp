package com.mehmetth.bitcointickerapp.utils

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore

class Constant {
    companion object {
        @SuppressLint("StaticFieldLeak")
        val FIREBASEFIRESTOREDB= FirebaseFirestore.getInstance()

        const val VSCURRENCY = "usd"
        const val VSCURRENCYSYMBOL = "$"
        const val FAVORITECOINS = "FavoriteCoins"
    }
}