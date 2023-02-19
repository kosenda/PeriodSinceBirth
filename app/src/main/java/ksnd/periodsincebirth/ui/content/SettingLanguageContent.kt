package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.parts.CustomTextButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@Composable
fun SettingLanguageContent() {
    TitleCard(
        text = stringResource(id = R.string.language_setting),
        painter = painterResource(id = R.drawable.baseline_language_24),
    )
    CustomTextButton(
        text = stringResource(id = R.string.select_language),
        onClick = { /* Dialogを表示して言語を選択できるようにする */ }
    )
}

@Preview
@Composable
fun PreviewSettingLanguageContent_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            Column {
                SettingLanguageContent()
            }
        }
    }
}

@Preview
@Composable
fun PreviewSettingLanguageContent_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            Column {
                SettingLanguageContent()
            }
        }
    }
}