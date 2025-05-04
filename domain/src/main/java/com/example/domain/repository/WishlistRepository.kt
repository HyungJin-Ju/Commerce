package com.example.domain.repository

import com.example.domain.model.Product

interface WishlistRepository {
    suspend fun getWishlist(): List<Product>
    suspend fun addToWishlist(product: Product)
    suspend fun removeFromWishlist(product: Product)
    suspend fun isWishlisted(productId: Long): Boolean
}