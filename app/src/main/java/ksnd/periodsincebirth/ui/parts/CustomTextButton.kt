package ksnd.periodsincebirth.ui.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.rememberButtonScaleState
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    val buttonScaleState = rememberButtonScaleState()
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .padding(vertical = 8.dp)
            .height(56.dp)
            .scale(scale = buttonScaleState.animationScale.value)
            .clickable(
                interactionSource = buttonScaleState.interactionSource,
                indication = null,
                onClick = onClick,
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                contentDescription = "move screen",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant),
                modifier = Modifier.padding(end = 8.dp).size(32.dp),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCustomButton_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            CustomTextButton(text = stringResource(R.string.oss_licenses), onClick = {})
        }
    }
}

@Preview
@Composable
private fun PreviewCustomButton_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            CustomTextButton(text = stringResource(R.string.oss_licenses), onClick = {})
        }
    }
}
