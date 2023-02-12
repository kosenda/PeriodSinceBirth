package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.ui.parts.PeriodSinceBirthCard
import java.time.ZonedDateTime

@Composable
fun PeriodSinceBirthScreen(birthday: ZonedDateTime) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        PeriodSinceBirthCard(birthday = birthday)
    }
}