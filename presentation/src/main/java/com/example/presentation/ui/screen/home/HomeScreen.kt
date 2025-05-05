package com.example.presentation.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.ui.screen.home.item.ProductList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val sectionItems = viewModel.sectionUiPagingFlow.collectAsLazyPagingItems()
    val sectionProducts = viewModel.sectionProducts.collectAsState()

    val isRefreshing = sectionItems.loadState.refresh is LoadState.Loading

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { sectionItems.refresh() },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
        ) {
            items(sectionItems.itemCount) { index ->
                val section = sectionItems[index]
                section?.let {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                    )

                    val products = sectionProducts.value[it.id]
                    if (products == null) {
                        LaunchedEffect(it.id) {
                            viewModel.loadProducts(it.id)
                        }
                        Text("로딩 중...", modifier = Modifier.padding(8.dp))
                    } else {
                        ProductList(
                            products = products,
                            type = it.type,
                            onWishlistToggle = { viewModel.toggleWishlist(it, section.id) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            sectionItems.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item {
                            Text("추가 로딩 중...", modifier = Modifier.padding(16.dp))
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item {
                            Text("에러 발생: ${e.error.localizedMessage}", modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}
