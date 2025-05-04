package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.WishlistEntity

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist")
    suspend fun getAll(): List<WishlistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: WishlistEntity)

    @Delete
    suspend fun delete(product: WishlistEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM wishlist WHERE productId = :id)")
    suspend fun isWishlisted(id: Long): Boolean
}