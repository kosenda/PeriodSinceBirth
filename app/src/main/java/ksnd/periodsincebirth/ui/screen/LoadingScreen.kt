package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.reducer.appReducer
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.ui.NavigationItems
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.createStore

@Composable
fun LoadingScreen() {
    val dispatch = rememberDispatcher()
    LaunchedEffect(Unit) {
        dispatch(AppAction.FetchBirthday)
    }
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .then(Modifier.size(48.dp)),
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = stringResource(id = R.string.loading),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
fun LoadingScreen_Light() {
    StoreProvider(
        store = createStore(
            reducer = appReducer,
            preloadedState = AppState(birthday = null, navState = NavigationItems.Loading),
        ),
    ) {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            LoadingScreen()
        }
    }
}

@Preview
@Composable
fun LoadingScreen_Dark() {
    StoreProvider(
        store = createStore(
            reducer = appReducer,
            preloadedState = AppState(birthday = null, navState = NavigationItems.Loading),
        ),
    ) {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            LoadingScreen()
        }
    }
}