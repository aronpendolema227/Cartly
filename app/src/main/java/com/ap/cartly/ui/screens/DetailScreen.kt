package com.ap.cartly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ap.cartly.model.Product
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.ap.cartly.R

@Composable
fun DetailScreen(
    product: Product?,
    onBack: () -> Unit
) {
    val producto = product

    if (producto == null) {
        ProductoNoEncontrado(
            onBack = onBack
        )

        return
    }

    var cargandoImagen by remember(producto.imageUrl) {
        mutableStateOf(true)
    }

    var errorImagen by remember(producto.imageUrl) {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 20.dp,
                vertical = 12.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_arrowback
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(
                    modifier = Modifier.size(6.dp)
                )

                Text(
                    text = "Regresar"
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Detalle del producto",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    color = obtenerColorCategoriaDetalle(
                        producto.category
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = producto.imageUrl,
                contentDescription = producto.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onLoading = {
                    cargandoImagen = true
                    errorImagen = false
                },
                onSuccess = {
                    cargandoImagen = false
                    errorImagen = false
                },
                onError = {
                    cargandoImagen = false
                    errorImagen = true
                }
            )

            if (cargandoImagen) {
                CircularProgressIndicator(
                    modifier = Modifier.size(38.dp),
                    strokeWidth = 3.dp
                )
            }

            if (errorImagen) {
                Text(
                    text = "Sin imagen disponible",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Text(
            text = producto.category.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = producto.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 6.dp)
        )

        Text(
            text = "USD ${
                String.format(
                    Locale.US,
                    "%.2f",
                    producto.price
                )
            }",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Surface(
            color = if (producto.estaDisponible) {
                Color(0xFFE5F7EE)
            } else {
                Color(0xFFFFE5E5)
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.padding(top = 14.dp)
        ) {
            Text(
                text = if (producto.estaDisponible) {
                    "Disponible · ${producto.stock} unidades"
                } else {
                    "Producto agotado"
                },
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 7.dp
                ),
                color = if (producto.estaDisponible) {
                    Color(0xFF147A4B)
                } else {
                    Color(0xFFB3261E)
                },
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium
            )
        }

        DetailInfoCard(
            titulo = "Descripción"
        ) {
            Text(
                text = producto.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            )
        }

        DetailInfoCard(
            titulo = "Especificaciones"
        ) {
            producto.specifications.forEach { especificacion ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = especificacion,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        FilledTonalButton(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 24.dp
                )
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_arrowback
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            Text(
                text = "Regresar al catálogo"
            )
        }
    }
}

@Composable
private fun DetailInfoCard(
    titulo: String,
    contenido: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            contenido()
        }
    }
}

@Composable
private fun ProductoNoEncontrado(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📦",
            fontSize = 72.sp
        )

        Text(
            text = "Producto no encontrado",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 18.dp)
        )

        Text(
            text = "No fue posible encontrar la información del producto seleccionado.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp)
        )

        FilledTonalButton(
            onClick = onBack,
            modifier = Modifier.padding(top = 22.dp)
        ) {
            Text(
                text = "Regresar al catálogo"
            )
        }
    }
}



private fun obtenerColorCategoriaDetalle(
    categoria: String
): Color {
    return when (categoria) {
        "Computadoras" -> Color(0xFFDDEEFF)
        "Accesorios" -> Color(0xFFFFE9D6)
        "Almacenamiento" -> Color(0xFFE8E2FF)
        "Audio" -> Color(0xFFDDF7F1)
        else -> Color(0xFFF0F2F5)
    }
}