package com.example.core.di

import android.content.Context
import com.example.core.network.AssetFileProvider
import com.example.core.network.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMockInterceptor(@ApplicationContext context: Context): MockInterceptor {
        return MockInterceptor(context)
    }

//    @Provides
//    @Singleton
//    fun provideAssetFileProvider(@ApplicationContext context: Context): AssetFileProvider {
//        return AssetFileProvider(context)
//    }
}