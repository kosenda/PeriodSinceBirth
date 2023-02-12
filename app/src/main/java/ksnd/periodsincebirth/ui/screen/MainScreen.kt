package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ksnd.periodsincebirth.state.MainState
import ksnd.periodsincebirth.store.InputBirthdayStore
import ksnd.periodsincebirth.ui.content.InputMyBirthdayContent
import ksnd.periodsincebirth.ui.content.PeriodSinceBirthContent
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.selectState
import java.time.ZonedDateTime

@Composable
fun MainScreen(inputBirthdayStore: InputBirthdayStore = hiltViewModel()) {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.surface
    SideEffect {
        systemUiController.setStatusBarColor(color)
        systemUiController.setNavigationBarColor(color)
    }

    val myBirthday by selectState<MainState, ZonedDateTime?> { myBirthday }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        if (myBirthday == null) {
            StoreProvider(
                store = inputBirthdayStore.store,
            ) {
                InputMyBirthdayContent(isInitial = true, onClick = {/* TODO */})
            }
        } else {
            PeriodSinceBirthContent(myBirthday = myBirthday!!)
        }
    }
}
