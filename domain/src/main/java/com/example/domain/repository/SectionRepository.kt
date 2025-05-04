package com.example.domain.repository

import androidx.paging.PagingSource
import com.example.domain.model.Product
import com.example.domain.model.Section

interface SectionRepository {
    fun getSections(): PagingSource<Int, Section>
    suspend fun getSectionProducts(sectionId: Int): List<Product>
}