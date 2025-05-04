package com.example.presentation.ui.screen.home.item

sealed class ProductListType {
    data object Vertical : ProductListType()
    data object Horizontal : ProductListType()
    data class Grid(val columnCount: Int, val rowCount: Int) : ProductListType()
}