package com.example.domain.usecase

import com.example.domain.model.Product
import com.example.domain.repository.SectionRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: SectionRepository
) {
    suspend operator fun invoke(sectionId: Int): List<Product> = repository.getSectionProducts(sectionId)
}