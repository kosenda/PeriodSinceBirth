package ksnd.periodsincebirth.ui.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.state.InputBirthdayState
import ksnd.periodsincebirth.state.MainState
import ksnd.periodsincebirth.store.InputBirthdayStore
import ksnd.periodsincebirth.ui.parts.CustomOutlinedTextField
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState
import java.time.ZonedDateTime

@Composable
fun MainScreen(inputBirthdayStore: InputBirthdayStore = hiltViewModel()) {
    val myBirthday by selectState<MainState, ZonedDateTime?> { myBirthday }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        if (myBirthday == null) {
            StoreProvider(
                store = inputBirthdayStore.store
            ) {
                InputMyBirthdayContent()
            }
        } else {
            PeriodSinceBirthContent(myBirthday = myBirthday!!)
        }
    }
}

@Composable
fun InputMyBirthdayContent() {
    val inputYear by selectState<InputBirthdayState, String> { year }
    val inputMonth by selectState<InputBirthdayState, String> { month }
    val inputDay by selectState<InputBirthdayState, String> { day }
    val isChangeable by selectState<InputBirthdayState, Boolean> { isChangeable }
    val dispatch = rememberDispatcher()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(inputYear, inputMonth, inputDay) {
        dispatch(InputBirthdayAction.CheckInput)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CustomOutlinedTextField(
            text = inputYear,
            labelText = stringResource(id = R.string.year),
            onValueChange = { year -> dispatch(InputBirthdayAction.InputYear(year)) }
        )
        CustomOutlinedTextField(
            text = inputMonth,
            labelText = stringResource(id = R.string.month),
            onValueChange = { month -> dispatch(InputBirthdayAction.InputMonth(month)) }
        )
        CustomOutlinedTextField(
            text = inputDay,
            labelText = stringResource(id = R.string.day),
            onValueChange = { day -> dispatch(InputBirthdayAction.InputDay(day)) }
        )

    }
}


@Composable
fun PeriodSinceBirthContent(myBirthday: ZonedDateTime) {
}

fun convertStringFromCalendar(calendar: ZonedDateTime?): String {
    // TODO 仮
    return if (calendar == null) {
        " - "
    } else {
        ""
    }
}