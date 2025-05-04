package com.example.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.Product
import com.example.domain.repository.SectionRepository

class SectionProductPagingSource(
    private val sectionId: Int,
    private val repository: SectionRepository,
    private val pageSize: Int = 10
) : PagingSource<Int, Product>() {

    private var allProducts: List<Product>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 0

            // 최초 한 번만 전체 데이터 불러오기
            if (allProducts == null) {
                allProducts = repository.getSectionProducts(sectionId)
            }

            val fromIndex = page * pageSize
            val toIndex = minOf(fromIndex + pageSize, allProducts!!.size)

            if (fromIndex >= allProducts!!.size) {
                LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
            } else {
                LoadResult.Page(
                    data = allProducts!!.subList(fromIndex, toIndex),
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (toIndex < allProducts!!.size) page + 1 else null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}