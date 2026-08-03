package com.ap.cartly.model

enum class MembershipLevel(
    val discountPercentage: Int,
    val displayName: String
){
    BASIC(
        discountPercentage = 0,
        displayName = "Basico"
    ),

    SILVER(
        discountPercentage = 10,
        displayName = "Plata"
    ),

    GOLD(
        discountPercentage = 20,
        displayName = "Oro"
    )
}