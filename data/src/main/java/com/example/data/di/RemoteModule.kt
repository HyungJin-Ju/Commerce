package com.example.data.di

import com.example.core.network.MockInterceptor
import com.example.data.remote.api.SectionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kurly.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSectionApi(retrofit: Retrofit): SectionApi {
        return retrofit.create(SectionApi::class.java)
    }
}