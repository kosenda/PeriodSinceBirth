package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.Theme
import ksnd.periodsincebirth.ui.parts.CustomRadioButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@Composable
fun SettingThemeContent(
    onRadioButtonClick: (Theme) -> Unit,
    isSelectedNum: (Theme) -> Boolean,
) {
    val modeRadioResourceTripleList: List<Triple<Theme, String, Painter>> = listOf(
        Triple(
            Theme.NIGHT,
            stringResource(id = R.string.dark_mode),
            painterResource(id = R.drawable.baseline_dark_mode_24),
        ),
        Triple(
            Theme.LIGHT,
            stringResource(id = R.string.light_mode),
            painterResource(id = R.drawable.baseline_brightness_low_24),
        ),
        Triple(
            Theme.AUTO,
            stringResource(id = R.string.auto_mode),
            painterResource(id = R.drawable.baseline_brightness_auto_24),
        ),
    )

    TitleCard(
        text = stringResource(id = R.string.theme_setting),
        painter = painterResource(id = R.drawable.baseline_dark_mode_24),
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        modeRadioResourceTripleList.forEach { resource ->
            val (theme, displayThemeName, painter) = resource
            CustomRadioButton(
                isSelected = isSelectedNum(theme),
                buttonText = displayThemeName,
                painter = painter,
                onClick = { onRadioButtonClick(theme) },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSettingThemeContent_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            SettingThemeContent(onRadioButtonClick = {}, isSelectedNum = { true })
        }
    }
}

@Preview
@Composable
private fun PreviewSettingThemeContent_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            SettingThemeContent(onRadioButtonClick = {}, isSelectedNum = { true })
        }
    }
}