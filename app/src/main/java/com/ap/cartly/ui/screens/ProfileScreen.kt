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
fun ProfileScreen(
    onBack: () -> Unit
) {
    val usuario = AppData.usuarioActual

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
        Text(
            text = "Perfil de usuario"
        )

        Text(
            text = usuario.name
        )

        Text(
            text = usuario.email
        )

        Text(
            text = "Membresía: " +
                    usuario.membershipLevel.displayName
        )

        Text(
            text = "Descuento: " +
                    "${usuario.membershipLevel.discountPercentage}%"
        )

        Button(
            onClick = onBack
        ) {
            Text(
                text = "Regresar al catálogo"
            )
        }
    }
}

