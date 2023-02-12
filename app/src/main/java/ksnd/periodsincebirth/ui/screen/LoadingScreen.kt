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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ksnd.periodsincebirth.ui.NavigationItems
import java.time.ZonedDateTime

@Composable
fun LoadingScreen(
    transitionScreen: (NavigationItems) -> Unit,
    loadingBirthday: (ZonedDateTime) -> Unit, // TODO 永続化データを読み込む
) {
    LaunchedEffect(Unit) {
        // TODO 仮
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000L)
            transitionScreen(NavigationItems.InputBirthday)
        }
    }
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "loaging...") // TODO 仮
        }
    }
}
