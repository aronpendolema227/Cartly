package com.ap.cartly.model

data class TransactionItem(
    val product: Product,
    val quantity: Int
)

data class Transaction(
    val user: User,
    val items: List<TransactionItem>,
    val subtotal: Double,
    val discount: Double,
    val subtotalWithDiscount: Double,
    val iva: Double,
    val total: Double
)