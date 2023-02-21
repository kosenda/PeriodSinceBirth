package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.NavigationItems
import ksnd.periodsincebirth.ui.content.SettingFontContent
import ksnd.periodsincebirth.ui.content.SettingLanguageContent
import ksnd.periodsincebirth.ui.content.SettingOtherContent
import ksnd.periodsincebirth.ui.content.SettingThemeContent
import ksnd.periodsincebirth.ui.parts.TopBar
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    val settingState by selectState<State, SettingState> { settingState }
    val dispatch = rememberDispatcher()

    Scaffold(
        topBar = {
            TopBar(
                backScreen = {
                    dispatch(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            SettingThemeContent(
                onRadioButtonClick = { new -> dispatch(SettingAction.ChangeTheme(new)) },
                isSelectedNum = { theme -> theme == settingState.theme },
            )
            SettingLanguageContent()
            SettingFontContent()
            SettingOtherContent()
        }
    }
}

@Preview
@Composable
private fun PreviewSettingScreen_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            SettingScreen()
        }
    }
}

@Preview
@Composable
private fun PreviewSettingScreen_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            SettingScreen()
        }
    }
}
