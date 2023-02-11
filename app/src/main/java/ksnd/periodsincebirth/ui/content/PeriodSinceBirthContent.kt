package ksnd.periodsincebirth.ui.content

import androidx.compose.runtime.Composable
import java.time.ZonedDateTime

@Composable
fun PeriodSinceBirthContent(myBirthday: ZonedDateTime) {
}

fun convertStringFromCalendar(calendar: ZonedDateTime?): String {
    // TODO 仮
    return if (calendar == null) {
        " - "
    } else {
        ""
    }
}
