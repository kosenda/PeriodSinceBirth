package ksnd.periodsincebirth.ui.parts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.selectState

@Composable
fun AnimatedCountText(title: String, value: Long, duration: Int, isApprox: Boolean = false) {
    val ratio by remember { mutableStateOf(Animatable(0f)) }
    val settingState by selectState<State, SettingState> { settingState }
    LaunchedEffect(Unit) {
        when {
            settingState.useAnimationText -> {
                ratio.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = duration),
                )
            }
            else -> {
                ratio.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 0),
                )
            }
        }
    }
    Row(
        modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            textAlign = TextAlign.Center,
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
        )
        if (isApprox) {
            Text(
                textAlign = TextAlign.Center,
                text = "  ※",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "%,d".format((value.toFloat() * ratio.value).toLong()),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
    )
}

@Preview
@Composable
private fun PreviewAnimatedCountText_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            Column(Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
                AnimatedCountText(title = "title", value = 1, duration = 0)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAnimatedCountText_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            Column(Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
                AnimatedCountText(title = "title", value = 1, duration = 0)
            }
        }
    }
}
