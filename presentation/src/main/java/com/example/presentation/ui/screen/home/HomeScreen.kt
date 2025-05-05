package com.example.presentation.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.ui.screen.home.component.SectionItemView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val sectionItems = viewModel.sectionUiPagingFlow.collectAsLazyPagingItems()
    val sectionProducts by viewModel.sectionProducts.collectAsState()

    val isRefreshing = sectionItems.loadState.refresh is LoadState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { sectionItems.refresh() },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
        ) {
            items(sectionItems.itemCount) { index ->
                sectionItems[index]?.let { section ->
                    SectionItemView(
                        section = section,
                        products = sectionProducts[section.id],
                        onLoadProducts = { viewModel.loadProducts(section.id) },
                        onToggleWishlist = { viewModel.toggleWishlist(it, section.id) }
                    )
                }
            }

            with(sectionItems.loadState) {
                when {
                    append is LoadState.Loading -> item {
                        Text("로딩 중...", modifier = Modifier.padding(16.dp))
                    }

                    refresh is LoadState.Error -> item {
                        val e = refresh as LoadState.Error
                        Text("에러 발생: ${e.error.localizedMessage}", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
