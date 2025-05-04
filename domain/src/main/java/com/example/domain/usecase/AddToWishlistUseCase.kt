package com.example.domain.usecase

import com.example.domain.model.Product
import com.example.domain.repository.WishlistRepository
import javax.inject.Inject

class AddToWishlistUseCase @Inject constructor(
    private val repository: WishlistRepository
) {
    suspend operator fun invoke(product: Product) = repository.addToWishlist(product)
}