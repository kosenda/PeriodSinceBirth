package ksnd.periodsincebirth

import androidx.compose.runtime.Composable
import ksnd.periodsincebirth.reducer.appReducer
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.state.InputDateState
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.Store
import org.reduxkotlin.combineReducers
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.createStore

@Composable
fun PreviewStoreProvider(content: @Composable (Store<State>.() -> Unit)) {
    StoreProvider(
        store = createStore(
            reducer = combineReducers(appReducer, inputBirthdayReducer),
            preloadedState = State(
                appState = AppState(birthday = null, navState = NavigationItems.PeriodSinceBirth),
                inputDateState = InputDateState(year = "", month = "", day = ""),
                settingState = SettingState(theme = Theme.AUTO, locale = ""),
            ),
        ),
        content = content,
    )
}
