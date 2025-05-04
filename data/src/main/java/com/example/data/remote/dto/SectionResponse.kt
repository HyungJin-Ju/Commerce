package com.example.data.remote.dto

data class SectionListResponse(
    val data: List<SectionDto>,
    val paging: PagingDto?
)

data class SectionDto(
    val title: String,
    val id: Int,
    val type: String,
    val url: String
)