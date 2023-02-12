package ksnd.periodsincebirth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ksnd.periodsincebirth.actions.MainAction
import ksnd.periodsincebirth.state.MainState
import ksnd.periodsincebirth.store.MainStore
import ksnd.periodsincebirth.ui.screen.MainScreen
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.Middleware
import org.reduxkotlin.compose.StoreProvider
import java.time.ZonedDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val color = MaterialTheme.colorScheme.surface
            SideEffect {
                systemUiController.setStatusBarColor(color)
                systemUiController.setNavigationBarColor(color)
            }

            val mainStore: MainStore = hiltViewModel()
            PeriodSinceBirthTheme {
                StoreProvider(
                    store = mainStore.store,
                ) {
                    MainScreen(
                        registerBirthday = { new -> dispatch(MainAction.ChangeBirthday(new)) }
                    )
                }
            }
        }
    }
}