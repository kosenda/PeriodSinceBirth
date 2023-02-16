package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.reducer.appReducer
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.ui.NavigationItems
import ksnd.periodsincebirth.ui.parts.CustomIconButton
import ksnd.periodsincebirth.ui.parts.PeriodSinceBirthCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import ksnd.periodsincebirth.util.makeBirthday
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.createStore
import java.time.ZonedDateTime

@Composable
fun PeriodSinceBirthContent(birthday: ZonedDateTime) {
    val dispatch = rememberDispatcher()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            CustomIconButton(
                contentDescription = "change birthday",
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                onClick = { dispatch(AppAction.TransitionScreen(NavigationItems.ChangeBirthday)) },
            )
            CustomIconButton(
                contentDescription = "info",
                painter = painterResource(id = R.drawable.outline_info_24),
                onClick = { dispatch(AppAction.TransitionScreen(NavigationItems.Info)) },
            )
            CustomIconButton(
                contentDescription = "settings",
                painter = painterResource(id = R.drawable.outline_settings_24),
                onClick = { dispatch(AppAction.TransitionScreen(NavigationItems.Settings)) },
            )
        }
        PeriodSinceBirthCard(birthday = birthday)
    }
}

@Preview
@Composable
private fun PeriodSinceBirthContent_Light() {
    StoreProvider(
        store = createStore(
            reducer = appReducer,
            preloadedState = AppState(birthday = null, navState = NavigationItems.PeriodSinceBirth),
        ),
    ) {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            PeriodSinceBirthContent(birthday = makeBirthday("2000", "1", "1")!!)
        }
    }
}

@Preview
@Composable
private fun PeriodSinceBirthContent_Dark() {
    StoreProvider(
        store = createStore(
            reducer = appReducer,
            preloadedState = AppState(birthday = null, navState = NavigationItems.PeriodSinceBirth),
        ),
    ) {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            PeriodSinceBirthContent(birthday = makeBirthday("2000", "1", "1")!!)
        }
    }
}
