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
import ksnd.periodsincebirth.util.getDaysUntilNextBirthday
import ksnd.periodsincebirth.util.makeBirthday
import java.time.ZonedDateTime

@Composable
fun PeriodUntilNextBirthdayCard(birthday: ZonedDateTime) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(id = R.string.until_next_birthday),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                text = "%d %s".format(
                    getDaysUntilNextBirthday(birthday = birthday),
                    stringResource(id = R.string.day),
                ),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPeriodUntilNextBirthdayCard_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        PeriodUntilNextBirthdayCard(birthday = makeBirthday("2000", "1", "1")!!)
    }
}

@Preview
@Composable
private fun PreviewPeriodUntilNextBirthdayCard_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        PeriodUntilNextBirthdayCard(birthday = makeBirthday("2000", "1", "1")!!)
    }
}
