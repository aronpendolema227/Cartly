package com.ap.cartly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ap.cartly.data.AppData
import com.ap.cartly.model.Product
import java.util.Locale

import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.remember

@Composable
fun CatalogScreen(
    onProductClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    var categoriaSeleccionada by rememberSaveable {
        mutableStateOf("Todos")
    }

    val productosFiltrados = if (categoriaSeleccionada == "Todos") {
        AppData.productos
    } else {
        AppData.productos.filter { producto ->
            producto.category == categoriaSeleccionada
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {
        CatalogHeader(
            onProfileClick = onProfileClick
        )

        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 10.dp
            )
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(AppData.categorias) { categoria ->
                FilterChip(
                    selected = categoriaSeleccionada == categoria,
                    onClick = {
                        categoriaSeleccionada = categoria
                    },
                    label = {
                        Text(text = categoria)
                    },
                    shape = RoundedCornerShape(50.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 22.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Productos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${productosFiltrados.size} disponibles",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(
                bottom = 24.dp
            )
        ) {
            items(
                items = productosFiltrados,
                key = { producto ->
                    producto.id
                }
            ) { producto ->
                ProductCard(
                    producto = producto,
                    onClick = {
                        onProductClick(producto.id)
                    }
                )
            }
        }
    }
}

@Composable
private fun CatalogHeader(
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Cartly",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Tecnología para tu día a día",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        FilledTonalButton(
            onClick = onProfileClick
        ) {
            Text(
                text = "Mi perfil"
            )
        }
    }
}

@Composable
private fun ProductCard(
    producto: Product,
    onClick: () -> Unit
) {
    var cargandoImagen by remember(producto.imageUrl) {
        mutableStateOf(true)
    }

    var errorImagen by remember(producto.imageUrl) {
        mutableStateOf(false)
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        color = obtenerColorCategoria(producto.category)
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
                        modifier = Modifier.size(26.dp),
                        strokeWidth = 2.dp
                    )
                }

                if (errorImagen) {
                    Text(
                        text = "Sin imagen",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = producto.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = producto.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 3.dp)
                )

                Text(
                    text = "USD ${
                        String.format(
                            Locale.US,
                            "%.2f",
                            producto.price
                        )
                    }",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = if (producto.estaDisponible) {
                            Color(0xFFE5F7EE)
                        } else {
                            Color(0xFFFFE5E5)
                        },
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Text(
                            text = if (producto.estaDisponible) {
                                "Stock: ${producto.stock}"
                            } else {
                                "Agotado"
                            },
                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 4.dp
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (producto.estaDisponible) {
                                Color(0xFF147A4B)
                            } else {
                                Color(0xFFB3261E)
                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Ver detalle ›",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}



private fun obtenerColorCategoria(
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