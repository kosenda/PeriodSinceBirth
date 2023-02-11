package ksnd.periodsincebirth.state

import timber.log.Timber
import java.time.ZoneId
import java.time.ZonedDateTime

data class InputBirthdayState(
    val year: String,
    val month: String,
    val day: String,
    val isChangeable: Boolean = false,
) {
    fun isCheckOK(): Boolean {
        return try {
            ZonedDateTime.of(
                year.toInt(),
                month.toInt(),
                day.toInt(),
                0,
                0,
                0,
                0,
                ZoneId.systemDefault(),
            )
            return when {
                year.length == 4 && month.length in 1..2 && day.length in 1..2 -> true
                else -> false
            }
        } catch (e: Exception) {
            Timber.i(e)
            false
        }
    }
}
