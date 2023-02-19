package ksnd.periodsincebirth.actions

import ksnd.periodsincebirth.Theme

sealed interface SettingAction {
    object FetchSettings : SettingAction
    data class SetTheme(val theme: Theme) : SettingAction
    data class ChangeTheme(val theme: Theme) : SettingAction
    data class SetUseAnimationText(val useAnimate: Boolean) : SettingAction
    data class SwitchAnimationText(val useAnimate: Boolean) : SettingAction
}
