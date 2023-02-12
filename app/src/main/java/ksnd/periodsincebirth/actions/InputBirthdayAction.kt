package ksnd.periodsincebirth.actions

import java.time.ZonedDateTime

sealed interface InputBirthdayAction {
    data class InputYear(val year: String) : InputBirthdayAction
    data class InputMonth(val month: String) : InputBirthdayAction
    data class InputDay(val day: String) : InputBirthdayAction
    object CheckInput : InputBirthdayAction
}
