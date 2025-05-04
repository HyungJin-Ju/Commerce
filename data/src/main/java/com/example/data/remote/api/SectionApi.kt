package com.example.data.remote.api

import com.example.data.remote.dto.ProductListResponse
import com.example.data.remote.dto.SectionListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionApi {
    @GET("/sections")
    suspend fun getSections(
        @Query("page") page: Int
    ): SectionListResponse

    @GET("/section/products")
    suspend fun getSectionProducts(
        @Query("sectionId") sectionId: Int
    ): ProductListResponse
}