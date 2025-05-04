package com.example.data.repository

import com.example.data.local.datasource.WishlistLocalDataSourceImpl
import com.example.data.local.mapper.toDomain
import com.example.data.local.mapper.toEntity
import com.example.domain.model.Product
import com.example.domain.repository.WishlistRepository
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val localDataSource: WishlistLocalDataSourceImpl
) : WishlistRepository {

    override suspend fun getWishlist(): List<Product> =
        localDataSource.getAll().map { it.toDomain() }

    override suspend fun addToWishlist(product: Product) {
        localDataSource.insert(product.toEntity())
    }

    override suspend fun removeFromWishlist(product: Product) {
        localDataSource.delete(product.toEntity())
    }

    override suspend fun isWishlisted(productId: Long): Boolean =
        localDataSource.isWishlisted(productId)
}