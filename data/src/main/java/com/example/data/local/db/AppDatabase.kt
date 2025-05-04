package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.WishlistDao
import com.example.data.local.entity.WishlistEntity

@Database(entities = [WishlistEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
}