package ksnd.periodsincebirth.ui.parts

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ksnd.periodsincebirth.R

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
        }
    )
}