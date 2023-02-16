package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.store.InputBirthdayStore
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState
import java.time.ZonedDateTime

@Composable
fun FirstScreen() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.surface
    SideEffect {
        systemUiController.setStatusBarColor(color)
        systemUiController.setNavigationBarColor(color)
    }

    val navController = rememberNavController()
    val birthday by selectState<AppState, ZonedDateTime?> { birthday }
    val navState by selectState<AppState, NavigationItems> { navState }
    val dispatch = rememberDispatcher()

    LaunchedEffect(navState) {
        navController.navigate(navState.route) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) { saveState = true }
            }
            launchSingleTop = true
            restoreState = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationItems.PeriodSinceBirth.route,
        ) {
            composable(NavigationItems.InputBirthday.route) {
                val inputBirthdayStore: InputBirthdayStore = hiltViewModel()
                StoreProvider(
                    store = inputBirthdayStore.store,
                ) {
                    InputMyBirthdayContent(
                        isInitial = true,
                        registerNewBirthday = {
                            dispatch(AppAction.ChangeBirthday(it))
                            dispatch(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                        },
                    )
                }
            }
            composable(NavigationItems.ChangeBirthday.route) {
                val inputBirthdayStore: InputBirthdayStore = hiltViewModel()
                StoreProvider(
                    store = inputBirthdayStore.store,
                ) {
                    InputMyBirthdayContent(
                        isInitial = false,
                        backScreen = {
                            dispatch(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                        },
                        registerNewBirthday = {
                            dispatch(AppAction.ChangeBirthday(it))
                            dispatch(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                        },
                        savedBirthday = birthday,
                    )
                }
            }
            composable(NavigationItems.PeriodSinceBirth.route) {
                PeriodSinceBirthScreen(birthday = birthday)
            }
            composable(NavigationItems.Settings.route) {
            }
            composable(NavigationItems.Info.route) {
            }
        }
    }
}
