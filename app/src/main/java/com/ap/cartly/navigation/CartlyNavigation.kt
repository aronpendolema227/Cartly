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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ap.cartly.viewmodel.ProductViewModel
import com.ap.cartly.viewmodel.UserViewModel

@Composable
fun CartlyNavigation(
    productViewModel: ProductViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val products by productViewModel.products.collectAsState()
    val userProfile by userViewModel.userProfile.collectAsState()

    NavHost(
        navController = navController,
        startDestination = CartlyRoutes.CATALOG,
        modifier = modifier
    ) {
        composable(
            route = CartlyRoutes.CATALOG
        ) {
            CatalogScreen(
                products = products,
                onProductClick = { productId ->
                    navController.navigate(
                        CartlyRoutes.detail(productId)
                    )
                },
                onProfileClick = {
                    navController.navigate(
                        CartlyRoutes.PROFILE
                    )
                },
                onFavoriteClick = { product ->
                    productViewModel.toggleFavorite(product)
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

            val product = products.find { producto ->
                producto.id == productId
            }

            DetailScreen(
                product = product,
                onBack = {
                    navController.popBackStack()
                }
            )

        }

        composable(
            route = CartlyRoutes.PROFILE
        ) {
            ProfileScreen(
                user = userProfile,
                onUpdateProfile = { updatedUser ->
                    userViewModel.updateProfile(updatedUser)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

