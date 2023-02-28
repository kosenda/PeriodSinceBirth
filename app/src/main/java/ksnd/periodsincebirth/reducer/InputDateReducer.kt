package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.InputDateAction
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val inputBirthdayReducer: Reducer<State> = typedReducer<State, InputDateAction> { state, action ->
    state.run {
        copy(
            inputDateState = when (action) {
                is InputDateAction.InputYear -> inputDateState.copy(year = action.year)
                is InputDateAction.InputMonth -> inputDateState.copy(month = action.month)
                is InputDateAction.InputDay -> inputDateState.copy(day = action.day)
                is InputDateAction.CheckInput -> inputDateState
                is InputDateAction.SetBirthday -> inputDateState.copy(birthday = action.date)
            },
        )
    }
}
