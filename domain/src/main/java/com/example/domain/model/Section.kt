package com.example.domain.model

data class Section(
    val id: Int,
    val title: String,
    val type: String, // grid, horizontal, vertical
    val url: String
)