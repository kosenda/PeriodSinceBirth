package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .then(Modifier.size(48.dp)),
            color = MaterialTheme.colorScheme.tertiary,
        )
        Text(
            text = stringResource(id = R.string.loading),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
private fun LoadingContent_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            LoadingContent()
        }
    }
}

@Preview
@Composable
private fun LoadingContent_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            LoadingContent()
        }
    }
}
