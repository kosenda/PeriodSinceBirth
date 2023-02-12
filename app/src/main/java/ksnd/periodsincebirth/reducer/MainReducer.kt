package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.MainAction
import ksnd.periodsincebirth.state.MainState
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer
import timber.log.Timber

val mainReducer: Reducer<MainState> =
    typedReducer<MainState, MainAction> { state, action ->
        when (action) {
            is MainAction.ChangeBirthday -> {
                println("action(ChangeBirthday)よばれた: ${action.newMyBirthday}") // TODO 仮
                state.copy(myBirthday = action.newMyBirthday)
            }
            is MainAction.ShowSettingDialog -> state.copy(showSettingDialog = true)
            is MainAction.CloseSettingDialog -> state.copy(showSettingDialog = false)
        }
}
