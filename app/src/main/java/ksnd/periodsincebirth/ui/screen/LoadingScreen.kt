package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ksnd.periodsincebirth.actions.AppAction
import org.reduxkotlin.compose.rememberDispatcher

@Composable
fun LoadingScreen() {
    val dispatch = rememberDispatcher()
    LaunchedEffect(Unit) {
        dispatch(AppAction.FetchBirthday)
    }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "loading...")
        }
    }
}
