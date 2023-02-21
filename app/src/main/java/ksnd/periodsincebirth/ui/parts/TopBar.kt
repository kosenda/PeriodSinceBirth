package ksnd.periodsincebirth.ui.parts

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(backScreen: () -> Unit) {
    TopAppBar(
        title = {
            CustomIconButton(
                contentDescription = "back screen",
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                onClick = backScreen,
            )
        },
    )
}

@Preview
@Composable
private fun PreviewTopBar_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            TopBar {}
        }
    }
}

@Preview
@Composable
private fun PreviewTopBar_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            TopBar {}
        }
    }
}
