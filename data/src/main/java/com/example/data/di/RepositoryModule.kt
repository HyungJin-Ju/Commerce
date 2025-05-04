package com.example.data.di

import com.example.data.repository.SectionRepositoryImpl
import com.example.data.repository.WishlistRepositoryImpl
import com.example.domain.repository.SectionRepository
import com.example.domain.repository.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSectionRepository(
        impl: SectionRepositoryImpl
    ): SectionRepository

    @Binds
    @Singleton
    abstract fun bindWishlistRepository(
        impl: WishlistRepositoryImpl
    ): WishlistRepository
}