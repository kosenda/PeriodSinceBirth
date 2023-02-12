package ksnd.periodsincebirth.util

import java.time.ZoneId
import java.time.ZonedDateTime

fun makeBirthday(year: String, month: String, day: String): ZonedDateTime? {
    return try {
        if (isCorrectDate(year, month, day)) {
            makeZonedDateTime(year, month, day)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

private fun makeZonedDateTime(year: String, month: String, day: String): ZonedDateTime {
    return ZonedDateTime.of(
        year.toInt(),
        month.toInt(),
        day.toInt(),
        0,
        0,
        0,
        0,
        ZoneId.systemDefault(),
    )
}

private fun isCorrectDate(year: String, month: String, day: String): Boolean {
    return year.length == 4 && month.length in 1..2 && day.length in 1..2
}