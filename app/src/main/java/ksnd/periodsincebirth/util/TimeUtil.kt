package ksnd.periodsincebirth.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun makeBirthday(year: String, month: String, day: String): ZonedDateTime? {
    try {
        if (isCorrectDate(year, month, day).not()) return null
        val time = makeZonedDateTime(year, month, day)
        return when {
            time.isBefore(getNowDate()) -> time
            else -> null
        }
    } catch (e: Exception) {
        return null
    }
}

fun convertZoneTimeToStr(time: ZonedDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    return time.format(formatter)
}

fun untilNow(time: ZonedDateTime, chronoUnit: ChronoUnit): Long {
    return time.until(getNowDate(), chronoUnit)
}

private fun getNowDate(): ZonedDateTime {
    val now = ZonedDateTime.now(ZoneId.systemDefault())
    return makeZonedDateTime(
        year = now.year.toString(),
        month = now.monthValue.toString(),
        day = now.dayOfMonth.toString(),
    )
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
