package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.compose.selectState

@Composable
fun FirstScreen() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.surface
    SideEffect {
        systemUiController.setStatusBarColor(color)
        systemUiController.setNavigationBarColor(color)
    }

    val navController = rememberNavController()
    val appState by selectState<State, AppState> { appState }

    LaunchedEffect(appState.navState) {
        navController.navigate(appState.navState.route) {
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
                InputMyBirthdayContent(isInitial = true)
            }
            composable(NavigationItems.ChangeBirthday.route) {
                InputMyBirthdayContent(isInitial = false, savedBirthday = appState.birthday,)
            }
            composable(NavigationItems.PeriodSinceBirth.route) {
                PeriodSinceBirthScreen(birthday = appState.birthday)
            }
            composable(NavigationItems.Settings.route) {
            }
            composable(NavigationItems.Info.route) {
            }
        }
    }
}
