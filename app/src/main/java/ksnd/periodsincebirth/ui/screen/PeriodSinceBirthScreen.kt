package ksnd.periodsincebirth.ui.screen

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.ui.content.LoadingContent
import ksnd.periodsincebirth.ui.content.PeriodSinceBirthContent
import org.reduxkotlin.compose.rememberDispatcher
import java.time.ZonedDateTime

@Composable
fun PeriodSinceBirthScreen(birthday: ZonedDateTime?) {
    val dispatch = rememberDispatcher()
    LaunchedEffect(Unit) {
        dispatch(AppAction.FetchBirthday)
    }
    Surface {
        when (birthday) {
            null -> LoadingContent()
            else -> PeriodSinceBirthContent(birthday = birthday)
        }
    }
}
