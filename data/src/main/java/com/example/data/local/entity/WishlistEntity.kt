package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class WishlistEntity(
    @PrimaryKey val productId: Long,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val addedAt: Long = System.currentTimeMillis()
)