package com.ap.cartly

import android.app.Application
import com.ap.cartly.data.local.CartlyDatabase
import com.ap.cartly.data.repository.ProductRepository
import com.ap.cartly.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.ap.cartly.viewmodel.CartlyViewModelFactory

class CartlyApplication : Application() {

    val database by lazy {
        CartlyDatabase.getDatabase(this)
    }

    val productRepository by lazy {
        ProductRepository(database.productDao())
    }

    val userRepository by lazy {
        UserRepository(database.userDao())
    }

    val viewModelFactory by lazy {
        CartlyViewModelFactory(
            productRepository = productRepository,
            userRepository = userRepository
        )
    }

    private val applicationScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            productRepository.initializeProductsIfNeeded()
            userRepository.initializeUsersIfNeeded()
        }
    }
}

