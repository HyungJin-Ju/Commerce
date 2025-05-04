package com.example.data.local.datasource

import com.example.data.local.dao.WishlistDao
import com.example.data.local.entity.WishlistEntity
import javax.inject.Inject

class WishlistLocalDataSourceImpl @Inject constructor(
    private val dao: WishlistDao
) {
    suspend fun getAll(): List<WishlistEntity> = dao.getAll()

    suspend fun insert(product: WishlistEntity) = dao.insert(product)

    suspend fun delete(product: WishlistEntity) = dao.delete(product)

    suspend fun isWishlisted(id: Long): Boolean = dao.isWishlisted(id)
}