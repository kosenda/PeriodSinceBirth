package ksnd.periodsincebirth.actions

import ksnd.periodsincebirth.Theme
import ksnd.periodsincebirth.ui.FontType

sealed interface SettingAction {
    object FetchSettings : SettingAction
    data class SetTheme(val theme: Theme) : SettingAction
    data class ChangeTheme(val theme: Theme) : SettingAction
    data class SetUseAnimationText(val useAnimate: Boolean) : SettingAction
    data class SwitchAnimationText(val useAnimate: Boolean) : SettingAction
    object OpenSelectLanguageDialog : SettingAction
    object CloseSelectLanguageDialog : SettingAction
    data class SetLocale(val locale: String) : SettingAction
    data class SetFontType(val fontType: FontType) : SettingAction
    data class ChangeFont(val fontType: FontType) : SettingAction
}
