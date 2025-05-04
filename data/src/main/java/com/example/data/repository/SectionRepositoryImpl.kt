package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api.SectionApi
import com.example.data.remote.mapper.toDomain
import com.example.domain.model.Product
import com.example.domain.model.Section
import com.example.domain.repository.SectionRepository
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val sectionApi: SectionApi
) : SectionRepository {

    override fun getSections(): PagingSource<Int, Section> {
        return object : PagingSource<Int, Section>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> {
                return try {
                    val page = params.key ?: 1
                    val response = sectionApi.getSections(page)

                    val sections = response.data.map { it.toDomain() }
                    val nextKey = response.paging?.next_page

                    LoadResult.Page(
                        data = sections,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = nextKey
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Section>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                }
            }
        }
    }

    override suspend fun getSectionProducts(sectionId: Int): List<Product> {
        return sectionApi.getSectionProducts(sectionId).data.map { it.toDomain() }
    }
}