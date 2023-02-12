package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.store.InputBirthdayStore
import ksnd.periodsincebirth.ui.NavigationItems
import ksnd.periodsincebirth.ui.content.InputMyBirthdayContent
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.selectState
import java.time.ZonedDateTime

@Composable
fun FirstScreen(
    registerBirthday: (ZonedDateTime) -> Unit,
    transitionScreen: (NavigationItems) -> Unit,
) {
    val navController = rememberNavController()
    val myBirthday by selectState<AppState, ZonedDateTime?> { myBirthday }
    val navState by selectState<AppState, NavigationItems> { navState }

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
            startDestination = NavigationItems.Loading.route,
        ) {
            composable(NavigationItems.Loading.route) {
                LoadingScreen(
                    transitionScreen = transitionScreen,
                    loadingBirthday = registerBirthday,
                )
            }
            composable(NavigationItems.InputBirthday.route) {
                val inputBirthdayStore: InputBirthdayStore = hiltViewModel()
                StoreProvider(
                    store = inputBirthdayStore.store,
                ) {
                    InputMyBirthdayContent(
                        isInitial = true,
                        onClick = {
                            registerBirthday(it)
                            transitionScreen(NavigationItems.PeriodSinceBirth)
                        },
                    )
                }
            }
            composable(NavigationItems.PeriodSinceBirth.route) {
                PeriodSinceBirthContent(myBirthday = myBirthday!!)
            }
            composable(NavigationItems.Settings.route) {
            }
            composable(NavigationItems.Info.route) {
            }
        }
    }
}
