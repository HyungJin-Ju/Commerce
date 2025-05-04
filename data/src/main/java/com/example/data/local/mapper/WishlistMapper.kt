package com.example.data.local.mapper

import com.example.data.local.entity.WishlistEntity
import com.example.domain.model.Product

fun WishlistEntity.toDomain(): Product = Product(
    id = productId,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = false // 로컬엔 재고정보 없음
)

fun Product.toEntity(): WishlistEntity = WishlistEntity(
    productId = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice
)