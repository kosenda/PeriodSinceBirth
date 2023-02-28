package ksnd.periodsincebirth.store

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ksnd.periodsincebirth.middleware.AppMiddleware
import ksnd.periodsincebirth.middleware.InputDateMiddleware
import ksnd.periodsincebirth.middleware.SettingMiddleware
import ksnd.periodsincebirth.reducer.appReducer
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.reducer.settingReducer
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.combineReducers
import org.reduxkotlin.createStore
import javax.inject.Inject

@HiltViewModel
class MainStore @Inject constructor(
    appMiddleware: AppMiddleware,
    settingMiddleware: SettingMiddleware,
) : ViewModel() {
    val store = createStore(
        reducer = combineReducers(
            appReducer,
            inputBirthdayReducer,
            settingReducer,
        ),
        preloadedState = State(),
        enhancer = applyMiddleware(
            appMiddleware,
            InputDateMiddleware(),
            settingMiddleware,
        ),
    )
}
