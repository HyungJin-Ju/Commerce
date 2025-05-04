package com.example.data.remote.datasource

import com.example.data.remote.api.SectionApi
import com.example.data.remote.dto.ProductDto
import com.example.data.remote.dto.SectionDto
import javax.inject.Inject

class SectionRemoteDataSourceImpl @Inject constructor(
    private val api: SectionApi
) {

    suspend fun getSections(page: Int): List<SectionDto> {
        return api.getSections(page).data
    }

    suspend fun getProducts(sectionId: Int): List<ProductDto> {
        return api.getSectionProducts(sectionId).data
    }
}