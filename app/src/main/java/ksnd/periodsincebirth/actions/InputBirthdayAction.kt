package ksnd.periodsincebirth.actions

sealed interface InputBirthdayAction {
    data class InputYear(val year: String) : InputBirthdayAction
    data class InputMonth(val month: String) : InputBirthdayAction
    data class InputDay(val day: String) : InputBirthdayAction
    object CheckInput : InputBirthdayAction
}
