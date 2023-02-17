package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val inputBirthdayReducer: Reducer<State> =
    typedReducer<State, InputBirthdayAction> { state, action ->
        when (action) {
            is InputBirthdayAction.InputYear -> state.copy(
                inputBirthdayState = state.inputBirthdayState.copy(year = action.year),
            )
            is InputBirthdayAction.InputMonth -> state.copy(
                inputBirthdayState = state.inputBirthdayState.copy(month = action.month),
            )
            is InputBirthdayAction.InputDay -> state.copy(
                inputBirthdayState = state.inputBirthdayState.copy(day = action.day),
            )
            is InputBirthdayAction.CheckInput -> state
            is InputBirthdayAction.SetBirthday -> state.copy(
                inputBirthdayState = state.inputBirthdayState.copy(birthday = action.birthday),
            )
        }
    }
