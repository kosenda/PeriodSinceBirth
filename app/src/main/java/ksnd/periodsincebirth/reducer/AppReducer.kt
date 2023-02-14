package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.AppState
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val appReducer: Reducer<AppState> =
    typedReducer<AppState, AppAction> { state, action ->
        when (action) {
            is AppAction.ChangeBirthday -> state.copy(birthday = action.newBirthday)
            is AppAction.TransitionScreen -> state.copy(navState = action.next)
            is AppAction.FetchBirthday -> state
        }
    }
