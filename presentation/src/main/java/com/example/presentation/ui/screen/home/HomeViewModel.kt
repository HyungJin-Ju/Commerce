package com.example.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.usecase.AddToWishlistUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.GetSectionsUseCase
import com.example.domain.usecase.GetWishlistUseCase
import com.example.domain.usecase.RemoveFromWishlistUseCase
import com.example.presentation.ui.screen.home.item.ProductUiState
import com.example.presentation.ui.screen.home.item.SectionUiState
import com.example.presentation.ui.screen.home.item.toDomain
import com.example.presentation.ui.screen.home.item.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSectionListUseCase: GetSectionsUseCase,
    private val getSectionProductsUseCase: GetProductsUseCase,
    private val getWishlistUseCase: GetWishlistUseCase,
    private val addToWishlistUseCase: AddToWishlistUseCase,
    private val removeFromWishlistUseCase: RemoveFromWishlistUseCase
) : ViewModel() {

    val sectionUiPagingFlow: Flow<PagingData<SectionUiState>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { getSectionListUseCase() }
    ).flow
        .map { pagingData -> pagingData.map { it.toUiState() } }
        .cachedIn(viewModelScope)

    private val _sectionProducts = MutableStateFlow<Map<Int, List<ProductUiState>>>(emptyMap())
    val sectionProducts: StateFlow<Map<Int, List<ProductUiState>>> = _sectionProducts.asStateFlow()

    private val _wishlist = MutableStateFlow<Set<Long>>(emptySet())

    init {
        viewModelScope.launch {
            _wishlist.value = getWishlistUseCase().map { it.id }.toSet()
        }
    }

    fun loadProducts(sectionId: Int) {
        viewModelScope.launch {
            val products = getSectionProductsUseCase(sectionId)
                .map { it.toUiState(_wishlist.value.contains(it.id)) }

            _sectionProducts.update { it + (sectionId to products) }
        }
    }

    fun toggleWishlist(product: ProductUiState, sectionId: Int) {
        viewModelScope.launch {
            if (product.isWishlisted) {
                removeFromWishlistUseCase(product.toDomain())
            } else {
                addToWishlistUseCase(product.toDomain())
            }

            // 갱신된 위시리스트로 상태 반영
            _wishlist.value = getWishlistUseCase().map { it.id }.toSet()

            val updated = _sectionProducts.value[sectionId]?.map {
                it.copy(isWishlisted = _wishlist.value.contains(it.id))
            } ?: emptyList()

            _sectionProducts.update { it + (sectionId to updated) }
        }
    }
}