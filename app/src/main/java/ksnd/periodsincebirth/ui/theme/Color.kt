package ksnd.periodsincebirth.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun primaryBrush(): Brush = Brush.linearGradient(
    listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primary,
    ),
)

@Composable
fun secondaryBrush(): Brush = Brush.linearGradient(
    listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
    ),
)

fun Color.changeBrightness(value: Float = 1f) = this.copy(
    red = (this.red * value).coerceIn(0f, 1f),
    blue = (this.blue * value).coerceIn(0f, 1f),
    green = (this.green * value).coerceIn(0f, 1f),
)

fun Modifier.contentBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
        }
    }
