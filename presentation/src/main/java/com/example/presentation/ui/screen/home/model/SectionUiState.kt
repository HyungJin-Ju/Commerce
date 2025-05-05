package com.example.presentation.ui.screen.home.model

import androidx.compose.runtime.Immutable

@Immutable
data class SectionUiState(
    val id: Int,
    val title: String,
    val type: ProductListType
)