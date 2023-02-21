package ksnd.periodsincebirth.ui.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.rememberButtonScaleState
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import ksnd.periodsincebirth.ui.theme.changeBrightness

@Composable
fun IconAndTextButton(
    modifier: Modifier = Modifier,
    isClickable: Boolean,
    painter: Painter,
    text: String,
    onClick: () -> Unit,
) {
    val buttonScaleState = rememberButtonScaleState()
    Button(
        enabled = isClickable,
        modifier = modifier
            .height(52.dp)
            .alpha(alpha = if (isClickable) 1f else 0.5f)
            .scale(scale = buttonScaleState.animationScale.value)
            .background(
                color = if (isClickable.not()) {
                    MaterialTheme.colorScheme.primaryContainer.changeBrightness(1.03f)
                } else {
                    Color.Transparent
                },
                shape = CircleShape,
            )
            .shadow(shape = CircleShape, elevation = if (isClickable) 4.dp else 0.dp),
        shape = CircleShape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        interactionSource = buttonScaleState.interactionSource,
    ) {
        Image(
            painter = painter,
            contentDescription = text,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(36.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@Preview
@Composable
private fun PreviewBottomComposableButton_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            IconAndTextButton(
                isClickable = true,
                painter = painterResource(id = R.drawable.baseline_check_24),
                text = "TEMP",
            ) {}
        }
    }
}

@Preview
@Composable
private fun PreviewBottomComposableButton_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            IconAndTextButton(
                isClickable = true,
                painter = painterResource(id = R.drawable.baseline_check_24),
                text = "TEMP",
            ) {}
        }
    }
}
