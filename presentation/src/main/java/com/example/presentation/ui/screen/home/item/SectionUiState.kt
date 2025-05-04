package com.example.presentation.ui.screen.home.item

import androidx.compose.runtime.Immutable

@Immutable
data class SectionUiState(
    val id: Int,
    val title: String,
    val type: ProductListType
)