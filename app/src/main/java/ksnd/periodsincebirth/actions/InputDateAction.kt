package ksnd.periodsincebirth.actions

import java.time.ZonedDateTime

sealed interface InputDateAction {
    data class InputYear(val year: String) : InputDateAction
    data class InputMonth(val month: String) : InputDateAction
    data class InputDay(val day: String) : InputDateAction
    data class SetBirthday(val date: ZonedDateTime?) : InputDateAction
    data class CheckInput(val year: String, val month: String, val day: String) : InputDateAction
}
