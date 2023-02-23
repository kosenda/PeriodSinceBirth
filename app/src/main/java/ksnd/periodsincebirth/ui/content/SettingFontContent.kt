package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.FontType
import ksnd.periodsincebirth.ui.parts.CustomRadioButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun SettingFontContent() {
    val settingState by selectState<State, SettingState> { settingState }
    val dispatch = rememberDispatcher()

    TitleCard(
        text = stringResource(id = R.string.font_setting),
        painter = painterResource(id = R.drawable.baseline_text_fields_24),
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        FontType.values().map { fontType ->
            CustomRadioButton(
                isSelected = fontType == settingState.fontType,
                buttonText = fontType.fontName,
                onClick = { dispatch(SettingAction.ChangeFont(fontType = fontType)) },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSettingFontContent_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            SettingFontContent()
        }
    }
}

@Preview
@Composable
private fun PreviewSettingFontContent_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            SettingFontContent()
        }
    }
}
