package com.ap.cartly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ap.cartly.data.AppData
import com.ap.cartly.model.MembershipLevel

@Composable
fun ProfileScreen(
    onBack: () -> Unit
) {
    val usuario = AppData.usuarioActual

    val coloresMembresia = obtenerColoresMembresia(
        usuario.membershipLevel
    )

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
                Text(
                    text = "‹ Regresar"
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Mi perfil",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            coloresMembresia.primario,
                            coloresMembresia.secundario
                        )
                    ),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(
                    horizontal = 22.dp,
                    vertical = 28.dp
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.22f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = obtenerIniciales(usuario.name),
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = usuario.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = usuario.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Surface(
                    color = Color.White.copy(alpha = 0.20f),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        text = "Membresía ${
                            usuario.membershipLevel.displayName
                        }",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }
        }

        ProfileInfoCard(
            titulo = "Información personal"
        ) {
            ProfileInfoRow(
                etiqueta = "Nombre",
                valor = usuario.name
            )

            ProfileInfoRow(
                etiqueta = "Correo electrónico",
                valor = usuario.email
            )

            ProfileInfoRow(
                etiqueta = "Identificador",
                valor = usuario.id
            )
        }

        ProfileInfoCard(
            titulo = "Beneficios de membresía"
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = coloresMembresia.fondo,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(18.dp)
            ) {
                Column {
                    Text(
                        text = "${usuario.membershipLevel.discountPercentage}%",
                        style = MaterialTheme.typography.headlineMedium,
                        color = coloresMembresia.primario,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "de descuento en tus compras",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 3.dp)
                    )

                    Text(
                        text = obtenerDescripcionMembresia(
                            usuario.membershipLevel
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 10.dp)
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
            Text(
                text = "Regresar al catálogo"
            )
        }
    }
}

@Composable
private fun ProfileInfoCard(
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
                modifier = Modifier.padding(bottom = 12.dp)
            )

            contenido()
        }
    }
}

@Composable
private fun ProfileInfoRow(
    etiqueta: String,
    valor: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = etiqueta,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = valor,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 3.dp)
        )
    }
}

private data class ColoresMembresia(
    val primario: Color,
    val secundario: Color,
    val fondo: Color
)

private fun obtenerColoresMembresia(
    membresia: MembershipLevel
): ColoresMembresia {
    return when (membresia) {
        MembershipLevel.BASIC -> ColoresMembresia(
            primario = Color(0xFF1769AA),
            secundario = Color(0xFF00A896),
            fondo = Color(0xFFDDEEFF)
        )

        MembershipLevel.SILVER -> ColoresMembresia(
            primario = Color(0xFF667085),
            secundario = Color(0xFF98A2B3),
            fondo = Color(0xFFEAECF0)
        )

        MembershipLevel.GOLD -> ColoresMembresia(
            primario = Color(0xFFB7791F),
            secundario = Color(0xFFE5A93D),
            fondo = Color(0xFFFFF3D6)
        )
    }
}

private fun obtenerIniciales(
    nombre: String
): String {
    return nombre
        .trim()
        .split(" ")
        .filter { palabra ->
            palabra.isNotBlank()
        }
        .take(2)
        .joinToString("") { palabra ->
            palabra.first().uppercase()
        }
}

private fun obtenerDescripcionMembresia(
    membresia: MembershipLevel
): String {
    return when (membresia) {
        MembershipLevel.BASIC ->
            "Acceso al catálogo completo y beneficios básicos de Cartly."

        MembershipLevel.SILVER ->
            "Descuentos especiales y mejores beneficios en productos seleccionados."

        MembershipLevel.GOLD ->
            "El nivel más alto de Cartly, con el mayor descuento disponible."
    }
}