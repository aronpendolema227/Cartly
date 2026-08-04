package com.ap.cartly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ap.cartly.ui.screens.CatalogScreen
import com.ap.cartly.ui.screens.DetailScreen
import com.ap.cartly.ui.screens.ProfileScreen

@Composable
fun CartlyNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CartlyRoutes.CATALOG,
        modifier = modifier
    ) {
        composable(
            route = CartlyRoutes.CATALOG
        ) {
            CatalogScreen(
                onProductClick = { productId ->
                    navController.navigate(
                        CartlyRoutes.detail(productId)
                    )
                },
                onProfileClick = {
                    navController.navigate(
                        CartlyRoutes.PROFILE
                    )
                }
            )
        }

        composable(
            route = CartlyRoutes.DETAIL_WITH_ARGUMENT,
            arguments = listOf(
                navArgument(CartlyRoutes.PRODUCT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val productId = backStackEntry.arguments
                ?.getString(CartlyRoutes.PRODUCT_ID)

            DetailScreen(
                productId = productId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = CartlyRoutes.PROFILE
        ) {
            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

