package com.mehmetth.bitcointickerapp.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity

@Database(entities = [ServiceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}