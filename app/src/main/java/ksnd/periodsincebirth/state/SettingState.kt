package ksnd.periodsincebirth.state

import ksnd.periodsincebirth.Theme

data class SettingState(
    val theme: Theme,
    val locale: String,
    val useAnimationText: Boolean = true,
    val openSelectLanguageDialog: Boolean = false,
)
