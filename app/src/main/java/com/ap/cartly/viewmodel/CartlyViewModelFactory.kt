package com.ap.cartly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ap.cartly.data.repository.ProductRepository
import com.ap.cartly.data.repository.UserRepository

class CartlyViewModelFactory(
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(ProductViewModel::class.java) -> {
                ProductViewModel(productRepository) as T
            }

            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(userRepository) as T
            }

            else -> {
                throw IllegalArgumentException(
                    "ViewModel desconocido: ${modelClass.name}"
                )
            }
        }
    }
}

