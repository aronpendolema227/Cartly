package com.ap.cartly.navigation

object CartlyRoutes {

    const val CATALOG = "catalog"
    const val PROFILE = "profile"

    const val DETAIL = "detail"
    const val PRODUCT_ID = "productId"

    const val DETAIL_WITH_ARGUMENT =
        "$DETAIL/{$PRODUCT_ID}"

    fun detail(productId: String): String {
        return "$DETAIL/$productId"
    }
}