package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.AppState
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val mainReducer: Reducer<AppState> =
    typedReducer<AppState, AppAction> { state, action ->
        when (action) {
            is AppAction.ChangeBirthday -> state.copy(myBirthday = action.newMyBirthday)
            is AppAction.TransitionScreen -> state.copy(navState = action.next)
        }
}
