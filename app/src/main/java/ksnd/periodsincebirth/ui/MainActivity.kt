package ksnd.periodsincebirth.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ksnd.periodsincebirth.Theme
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.store.MainStore
import ksnd.periodsincebirth.ui.screen.NavScreen
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val mainStore: MainStore = hiltViewModel()
            StoreProvider(
                store = mainStore.store,
            ) {
                val settingState by selectState { settingState }
                val dispatch = rememberDispatcher()

                LaunchedEffect(Unit) {
                    dispatch(SettingAction.FetchSettings)
                }
                PeriodSinceBirthTheme(
                    isDarkTheme = when (settingState.theme) {
                        Theme.NIGHT -> true
                        Theme.LIGHT -> false
                        else -> isSystemInDarkTheme()
                    },
                    fontType = settingState.fontType,
                ) {
                    NavScreen()
                }
            }
        }
    }
}
