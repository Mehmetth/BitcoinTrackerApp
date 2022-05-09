package com.mehmetth.bitcointickerapp.domain.common

import android.content.Context
import androidx.room.Room
import com.mehmetth.bitcointickerapp.domain.db.AppDatabase
import com.mehmetth.bitcointickerapp.domain.db.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): CoinDao {
        return appDatabase.coinDao()
    }
}