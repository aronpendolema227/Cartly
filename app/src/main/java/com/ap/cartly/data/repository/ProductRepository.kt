package com.ap.cartly.data.repository

import com.ap.cartly.data.AppData
import com.ap.cartly.data.local.dao.ProductDao
import com.ap.cartly.data.mapper.toEntity
import com.ap.cartly.data.mapper.toModel
import com.ap.cartly.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(
    private val productDao: ProductDao
) {

    val products: Flow<List<Product>> =
        productDao.getAllProducts().map { entities ->
            entities.map { entity ->
                entity.toModel()
            }
        }

    fun getProductById(productId: String): Flow<Product?> {
        return productDao.getProductById(productId).map { entity ->
            entity?.toModel()
        }
    }

    suspend fun insert(product: Product) {
        productDao.insert(product.toEntity())
    }

    suspend fun insertAll(products: List<Product>) {
        productDao.insertAll(
            products.map { product ->
                product.toEntity()
            }
        )
    }

    suspend fun update(product: Product) {
        productDao.update(product.toEntity())
    }

    suspend fun delete(product: Product) {
        productDao.delete(product.toEntity())
    }

    suspend fun updateFavorite(
        productId: String,
        isFavorite: Boolean
    ) {
        productDao.updateFavorite(
            productId = productId,
            isFavorite = isFavorite
        )
    }

    suspend fun initializeProductsIfNeeded() {
        if (productDao.countProducts() == 0) {
            insertAll(AppData.productos)
        }
    }
}

