package com.mehmetth.bitcointickerapp.domain.db

import androidx.room.*
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity

@Dao
interface  CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coinItem: ServiceEntity)

    @Query("SELECT * FROM serviceentity WHERE name LIKE '%' || :parameter || '%' OR symbol LIKE '%' || :parameter || '%'")
    fun getCoinDetail(parameter: String): List<ServiceEntity>
}