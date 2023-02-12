package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.util.convertZoneTimeToStr
import ksnd.periodsincebirth.util.untilNow
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Composable
fun PeriodSinceBirthContent(myBirthday: ZonedDateTime) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = stringResource(id = R.string.birth_date))
        Text(text = convertZoneTimeToStr(myBirthday))
        Text(text = untilNow(time = myBirthday, ChronoUnit.YEARS))
        Text(text = untilNow(time = myBirthday, ChronoUnit.MONTHS))
        Text(text = untilNow(time = myBirthday, ChronoUnit.DAYS))
        Text(text = untilNow(time = myBirthday, ChronoUnit.HOURS))
        Text(text = untilNow(time = myBirthday, ChronoUnit.MINUTES))
        Text(text = untilNow(time = myBirthday, ChronoUnit.SECONDS))
    }
}
