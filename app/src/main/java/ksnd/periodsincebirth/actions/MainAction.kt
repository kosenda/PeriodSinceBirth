package ksnd.periodsincebirth.actions

import java.util.Calendar

sealed interface MainAction {
    data class ChangeBirthday(val newMyBirthday: Calendar) : MainAction
    object ShowSettingDialog : MainAction
    object CloseSettingDialog : MainAction
}