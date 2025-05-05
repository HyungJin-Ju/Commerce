package com.example.presentation.ui.screen.home.model

sealed class ProductListType {
    data object Vertical : ProductListType()
    data object Horizontal : ProductListType()
    data class Grid(val columnCount: Int, val rowCount: Int) : ProductListType()
}