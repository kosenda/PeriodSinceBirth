package ksnd.periodsincebirth.actions

import ksnd.periodsincebirth.Theme

sealed interface SettingAction {
    object FetchTheme : SettingAction
    data class SetTheme(val theme: Theme) : SettingAction
    data class ChangeTheme(val theme: Theme) : SettingAction
}