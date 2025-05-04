package com.example.domain.usecase

import com.example.domain.repository.WishlistRepository
import javax.inject.Inject

class IsWishlistedUseCase @Inject constructor(
    private val repository: WishlistRepository
) {
    suspend operator fun invoke(productId: Long): Boolean = repository.isWishlisted(productId)
}