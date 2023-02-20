package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.dialog.SelectLanguageDialog
import ksnd.periodsincebirth.ui.parts.CustomTextButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun SettingLanguageContent() {
    val dispatch = rememberDispatcher()
    val settingState by selectState<State, SettingState> { settingState }
    TitleCard(
        text = stringResource(id = R.string.language_setting),
        painter = painterResource(id = R.drawable.baseline_language_24),
    )

    if (settingState.openSelectLanguageDialog) {
        SelectLanguageDialog()
    }

    CustomTextButton(
        text = stringResource(id = R.string.select_language),
        onClick = { dispatch(SettingAction.OpenSelectLanguageDialog) },
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
