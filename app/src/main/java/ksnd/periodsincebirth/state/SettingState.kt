package ksnd.periodsincebirth.state

import ksnd.periodsincebirth.Theme
import ksnd.periodsincebirth.ui.FontType

data class SettingState(
    val theme: Theme = Theme.AUTO,
    val locale: String = "",
    val fontType: FontType = FontType.DEFAULT,
    val useAnimationText: Boolean = true,
    val openSelectLanguageDialog: Boolean = false,
)
