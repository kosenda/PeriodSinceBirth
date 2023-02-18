package ksnd.periodsincebirth.actions

sealed interface SettingAction {
    object FetchTheme : SettingAction
}