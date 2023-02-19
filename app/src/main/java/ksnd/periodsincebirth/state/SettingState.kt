package ksnd.periodsincebirth.state

import ksnd.periodsincebirth.Theme

data class SettingState(
    val theme: Theme,
    val language: String,
    val useAnimationText: Boolean = true,
)