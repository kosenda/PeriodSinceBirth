package ksnd.periodsincebirth.actions

import java.time.ZonedDateTime

sealed interface MainAction {
    data class ChangeBirthday(val newMyBirthday: ZonedDateTime) : MainAction
    object ShowSettingDialog : MainAction
    object CloseSettingDialog : MainAction
}