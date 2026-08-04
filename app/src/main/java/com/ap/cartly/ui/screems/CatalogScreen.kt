package com.ap.cartly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ap.cartly.data.AppData

@Composable
fun CatalogScreen(
    onProductClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    val primerProducto = AppData.productos.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Catálogo de Cartly"
        )

        Button(
            onClick = onProfileClick
        ) {
            Text(
                text = "Ver perfil"
            )
        }

        Button(
            onClick = {
                primerProducto?.let { producto ->
                    onProductClick(producto.id)
                }
            },
            enabled = primerProducto != null
        ) {
            Text(
                text = "Abrir producto de prueba"
            )
        }
    }
}

