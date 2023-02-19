package ksnd.periodsincebirth.reducer

import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

val settingReducer: Reducer<State> =
    typedReducer<State, SettingAction> { state, action ->
        state.run {
            copy(
                settingState = when (action) {
                    is SettingAction.FetchSettings -> settingState
                    is SettingAction.SetTheme -> settingState.copy(theme = action.theme)
                    is SettingAction.ChangeTheme -> settingState.copy(theme = action.theme)
                    is SettingAction.SetUseAnimationText ->
                        settingState.copy(useAnimationText = action.useAnimate)
                    is SettingAction.SwitchAnimationText ->
                        settingState.copy(useAnimationText = action.useAnimate)
                },
            )
        }
    }
