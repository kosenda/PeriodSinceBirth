package ksnd.periodsincebirth.ui.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import ksnd.periodsincebirth.util.convertZoneTimeToStr
import ksnd.periodsincebirth.util.makeBirthday
import ksnd.periodsincebirth.util.untilNow
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Composable
fun PeriodSinceBirthCard(birthday: ZonedDateTime) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.since_birth),
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            AnimatedCountText(
                value = untilNow(time = birthday, ChronoUnit.YEARS),
                duration = 250,
                title = stringResource(id = R.string.years)
            )
            AnimatedCountText(
                value = untilNow(time = birthday, ChronoUnit.MONTHS),
                duration = 500,
                title = stringResource(id = R.string.months)
            )
            AnimatedCountText(
                value = untilNow(time = birthday, ChronoUnit.DAYS),
                duration = 750,
                title = stringResource(id = R.string.days)
            )
            AnimatedCountText(
                value = untilNow(time = birthday, ChronoUnit.HOURS),
                duration = 1000,
                title = stringResource(id = R.string.hours),
                isApprox = true,
            )
            AnimatedCountText(
                value = untilNow(time = birthday, ChronoUnit.MINUTES),
                duration = 1250,
                title = stringResource(id = R.string.minutes),
                isApprox = true,
            )
            AnimatedCountText(
                value = untilNow(time = birthday, ChronoUnit.SECONDS),
                duration = 1500,
                title = stringResource(id = R.string.seconds),
                isApprox = true,
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = "※ %s".format(stringResource(id = R.string.approximately)),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Right,
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                text = "%s: %s".format(
                    stringResource(id = R.string.birth_date), convertZoneTimeToStr(birthday)),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Right,
            )
        }
    }
}

@Preview
@Composable
fun PreviewPeriodSinceBirthCard_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        PeriodSinceBirthCard(birthday = makeBirthday(year = "2000", month = "1", day = "1")!!)
    }
}

@Preview
@Composable
fun PreviewPeriodSinceBirthCard_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        PeriodSinceBirthCard(birthday = makeBirthday(year = "2000", month = "1", day = "1")!!)
    }
}