package com.mehmetth.bitcointickerapp.data.service

import com.mehmetth.bitcointickerapp.data.common.NetworkModule
import com.mehmetth.bitcointickerapp.data.service.remote.api.ServiceApi
import com.mehmetth.bitcointickerapp.data.service.repository.ServicesRepositoryImpl
import com.mehmetth.bitcointickerapp.domain.service.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit) : ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(serviceApi: ServiceApi) : ServiceRepository {
        return ServicesRepositoryImpl(serviceApi)
    }
}