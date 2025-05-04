package com.example.presentation.ui.screen.home

import com.example.presentation.ui.screen.home.item.ProductUiState
import com.example.presentation.ui.screen.home.item.SectionUiState

data class HomeUiState(
    val section: SectionUiState? = null,
    val products: List<ProductUiState> = emptyList()
)