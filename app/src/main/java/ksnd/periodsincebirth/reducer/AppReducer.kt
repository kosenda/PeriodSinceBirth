package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val appReducer: Reducer<State> =
    typedReducer<State, AppAction> { state, action ->
        state.run {
            copy(
                appState = when (action) {
                    is AppAction.SetBirthday -> appState.copy(birthday = action.newBirthday)
                    is AppAction.ChangeBirthday -> appState.copy(birthday = action.newBirthday)
                    is AppAction.TransitionScreen -> appState.copy(navState = action.next)
                    is AppAction.FetchBirthday -> appState
                },
            )
        }
    }
