package com.ap.cartly.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    private var stockDisponible: Int,

    // Informacion necesaria para la interfas
    val category: String = "General",
    val description: String = "",
    val specifications: List<String> = emptyList(),
    val imageUrl: String = "",
    val isFavorite: Boolean = false
){
    val stock: Int
        get() = stockDisponible

    val estaDisponible: Boolean
        get() = stockDisponible > 0

    fun tieneStock(cantidad: Int): Boolean {
        return cantidad > 0 && cantidad <= stockDisponible
    }
    fun descontarStock(cantidad: Int): Boolean{
        if (!tieneStock(cantidad)) {
            return false
        }

        stockDisponible -= cantidad
        return true
    }
}