package com.ap.cartly.data.mapper

import com.ap.cartly.data.local.entity.ProductEntity
import com.ap.cartly.model.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        isFavorite = isFavorite,
        stock = stock,
        category = category,
        specifications = specifications.joinToString("||"),
        imageUrl = imageUrl
    )
}

fun ProductEntity.toModel(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        stockDisponible = stock,
        category = category,
        description = description,
        specifications = if (specifications.isBlank()) {
            emptyList()
        } else {
            specifications.split("||")
        },
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )
}

