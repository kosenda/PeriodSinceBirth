package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.state.InputBirthdayState
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val inputBirthdayReducer: Reducer<InputBirthdayState> =
    typedReducer<InputBirthdayState, InputBirthdayAction> { state, action ->
        when (action) {
            is InputBirthdayAction.InputYear -> state.copy(year = action.year)
            is InputBirthdayAction.InputMonth -> state.copy(month = action.month)
            is InputBirthdayAction.InputDay -> state.copy(day = action.day)
            is InputBirthdayAction.CheckInput -> state
            is InputBirthdayAction.SetBirthday -> state.copy(birthday = action.birthday)
        }
    }
