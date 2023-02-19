package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.parts.InfoItemBody
import ksnd.periodsincebirth.ui.parts.InfoItemTitle
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@Composable
fun DeveloperInfoContent() {
    TitleCard(
        text = stringResource(id = R.string.developer_info_title),
        painter = painterResource(id = R.drawable.outline_info_24),
    )
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.google_play_icon),
                contentDescription = "convert",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .size(72.dp)
                    .clip(CircleShape),
            )
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                InfoItemTitle(
                    text = stringResource(id = R.string.developer_name_title),
                    modifier = Modifier.padding(bottom = 4.dp),
                )
                InfoItemBody(text = stringResource(id = R.string.developer_name))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDeveloperInfoContent_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        DeveloperInfoContent()
    }
}

@Preview
@Composable
private fun PreviewDeveloperInfoContent_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        DeveloperInfoContent()
    }
}