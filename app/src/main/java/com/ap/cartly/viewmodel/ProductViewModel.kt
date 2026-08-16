package com.ap.cartly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ap.cartly.data.repository.ProductRepository
import com.ap.cartly.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    val products: StateFlow<List<Product>> =
        productRepository.products.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun getProductById(productId: String): Flow<Product?> {
        return productRepository.getProductById(productId)
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            productRepository.updateFavorite(
                productId = product.id,
                isFavorite = !product.isFavorite
            )
        }
    }

    fun updateFavorite(
        productId: String,
        isFavorite: Boolean
    ) {
        viewModelScope.launch {
            productRepository.updateFavorite(
                productId = productId,
                isFavorite = isFavorite
            )
        }
    }
}
