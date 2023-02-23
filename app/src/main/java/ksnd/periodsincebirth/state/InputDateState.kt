package ksnd.periodsincebirth.state

import java.time.ZonedDateTime

data class InputDateState(
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val isChangeable: Boolean = false,
    val birthday: ZonedDateTime? = null,
)
