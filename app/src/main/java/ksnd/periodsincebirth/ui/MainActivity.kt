package ksnd.periodsincebirth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.store.AppStore
import ksnd.periodsincebirth.ui.screen.FirstScreen
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.StoreProvider

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val firstStore: AppStore = hiltViewModel()
            PeriodSinceBirthTheme {
                StoreProvider(
                    store = firstStore.store,
                ) {
                    FirstScreen(
                        registerBirthday = { new -> dispatch(AppAction.ChangeBirthday(new)) },
                        transitionScreen = { item -> dispatch(AppAction.TransitionScreen(item)) },
                    )
                }
            }
        }
    }
}
