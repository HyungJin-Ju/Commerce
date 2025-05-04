package com.example.data.remote.mapper

import com.example.data.remote.dto.ProductDto
import com.example.data.remote.dto.SectionDto
import com.example.domain.model.Product
import com.example.domain.model.Section

fun SectionDto.toDomain(): Section = Section(
    id = id,
    title = title,
    type = type,
    url = url
)

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut
)