package com.ap.cartly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ap.cartly.navigation.CartlyNavigation
import com.ap.cartly.ui.theme.CartlyTheme
import androidx.lifecycle.ViewModelProvider
import com.ap.cartly.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cartlyApplication = application as CartlyApplication

        val productViewModel = ViewModelProvider(
            this,
            cartlyApplication.viewModelFactory
        )[ProductViewModel::class.java]

        enableEdgeToEdge()

        setContent {
            CartlyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    CartlyNavigation(
                        productViewModel = productViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}