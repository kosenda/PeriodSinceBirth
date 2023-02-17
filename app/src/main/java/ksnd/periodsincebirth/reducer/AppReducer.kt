package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val appReducer: Reducer<State> =
    typedReducer<State, AppAction> { state, action ->
        when (action) {
            is AppAction.SetBirthday -> state.copy(
                appState = state.appState.copy(birthday = action.newBirthday),
            )
            is AppAction.ChangeBirthday -> state.copy(
                appState = state.appState.copy(birthday = action.newBirthday),
            )
            is AppAction.TransitionScreen -> state.copy(
                appState = state.appState.copy(navState = action.next),
            )
            is AppAction.FetchBirthday -> state
        }
    }
