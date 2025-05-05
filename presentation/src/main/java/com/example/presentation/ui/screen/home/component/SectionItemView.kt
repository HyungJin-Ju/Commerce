package com.example.presentation.ui.screen.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.screen.home.model.ProductUiState
import com.example.presentation.ui.screen.home.model.SectionUiState

@Composable
fun SectionItemView(
    section: SectionUiState,
    products: List<ProductUiState>?,
    onLoadProducts: () -> Unit,
    onToggleWishlist: (ProductUiState) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = section.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp, start = 8.dp)
        )

        if (products == null) {
            LaunchedEffect(section.id) { onLoadProducts() }
            Text("로딩 중...", modifier = Modifier.padding(8.dp))
        } else {
            ProductList(
                products = products,
                type = section.type,
                onWishlistToggle = onToggleWishlist,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}