package ksnd.periodsincebirth.ui.content

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.parts.BottomCloseButton
import ksnd.periodsincebirth.ui.parts.LanguageCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLanguageDialogContent() {
    val dispatch = rememberDispatcher()
    val settingState by selectState<State, SettingState> { settingState }
    val languagePair = listOf(
        stringResource(id = R.string.locale_en) to stringResource(id = R.string.display_en),
        stringResource(id = R.string.locale_ja) to stringResource(id = R.string.display_ja),
        stringResource(id = R.string.locale_zh_tw) to stringResource(id = R.string.display_zh_tw),
        stringResource(id = R.string.locale_tr) to stringResource(id = R.string.display_tr),
        stringResource(id = R.string.locale_vi) to stringResource(id = R.string.display_vi),
        stringResource(id = R.string.locale_it) to stringResource(id = R.string.display_it),
    )

    LaunchedEffect(Unit) {
        val locale = AppCompatDelegate.getApplicationLocales()[0]
        locale?.let { dispatch(SettingAction.SetLocale(locale = it.toLanguageTag())) }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxHeight(0.95f)
            .fillMaxWidth(0.95f)
            .clip(RoundedCornerShape(16.dp)),
        bottomBar = {
            BottomCloseButton(onClick = { dispatch(SettingAction.CloseSelectLanguageDialog) })
        },
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(items = languagePair) { (locale, displayLanguage) ->
                    LanguageCard(
                        displayLanguage = displayLanguage,
                        isSelected = settingState.locale == locale,
                        onClick = {
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags(locale),
                            )
                            dispatch(SettingAction.SetLocale(locale = locale))
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSelectLanguageDialogContent_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            SelectLanguageDialogContent()
        }
    }
}

@Preview
@Composable
private fun PreviewSelectLanguageDialogContent_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            SelectLanguageDialogContent()
        }
    }
}
