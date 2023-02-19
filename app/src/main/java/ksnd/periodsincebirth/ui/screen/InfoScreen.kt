package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.ui.NavigationItems
import ksnd.periodsincebirth.ui.content.AppInfoContent
import ksnd.periodsincebirth.ui.content.DeveloperInfoContent
import ksnd.periodsincebirth.ui.content.LicensesContent
import ksnd.periodsincebirth.ui.parts.TopBar
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.rememberDispatcher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen() {
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
            AppInfoContent()
            DeveloperInfoContent()
            LicensesContent()
        }
    }
}

@Preview
@Composable
private fun PreviewInfoScreen_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            InfoScreen()
        }
    }
}

@Preview
@Composable
private fun PreviewInfoScreen_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            InfoScreen()
        }
    }
}
