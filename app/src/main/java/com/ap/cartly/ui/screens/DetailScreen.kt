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
fun DetailScreen(
    productId: String?,
    onBack: () -> Unit
) {
    val producto = AppData.productos.find { producto ->
        producto.id == productId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (producto == null) {
            Text(
                text = "Producto no encontrado"
            )
        } else {
            Text(
                text = producto.name
            )

            Text(
                text = "$${String.format("%.2f", producto.price)}"
            )

            Text(
                text = producto.description
            )

            Text(
                text = "Stock disponible: ${producto.stock}"
            )
        }

        Button(
            onClick = onBack
        ) {
            Text(
                text = "Regresar al catálogo"
            )
        }
    }
}

