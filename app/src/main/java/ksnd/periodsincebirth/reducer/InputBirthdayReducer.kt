package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val inputBirthdayReducer: Reducer<State> =
    typedReducer<State, InputBirthdayAction> { state, action ->
        state.run {
            copy(
                inputBirthdayState = when (action) {
                    is InputBirthdayAction.InputYear ->
                        inputBirthdayState.copy(year = action.year)
                    is InputBirthdayAction.InputMonth ->
                        inputBirthdayState.copy(month = action.month)
                    is InputBirthdayAction.InputDay ->
                        inputBirthdayState.copy(day = action.day)
                    is InputBirthdayAction.CheckInput ->
                        inputBirthdayState
                    is InputBirthdayAction.SetBirthday ->
                        inputBirthdayState.copy(birthday = action.birthday)
                },
            )
        }
    }
