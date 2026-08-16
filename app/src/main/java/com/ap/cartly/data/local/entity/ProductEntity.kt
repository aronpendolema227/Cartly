package com.ap.cartly.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,

    val name: String,
    val description: String,
    val price: Double,
    val isFavorite: Boolean = false,

    val stock: Int,
    val category: String,
    val specifications: String,
    val imageUrl: String
)

