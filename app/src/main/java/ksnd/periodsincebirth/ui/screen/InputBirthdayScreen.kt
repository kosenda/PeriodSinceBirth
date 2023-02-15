package ksnd.periodsincebirth.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.state.InputBirthdayState
import ksnd.periodsincebirth.ui.parts.CustomOutlinedTextField
import ksnd.periodsincebirth.ui.parts.IconAndTextButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.parts.TopBar
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import ksnd.periodsincebirth.ui.theme.contentBrush
import ksnd.periodsincebirth.ui.theme.secondaryBrush
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState
import org.reduxkotlin.createStore
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputMyBirthdayContent(
    isInitial: Boolean,
    backScreen: () -> Unit = {},
    registerNewBirthday: (ZonedDateTime) -> Unit,
    savedBirthday: ZonedDateTime? = null,
) {
    val inputYear by selectState<InputBirthdayState, String> { year }
    val inputMonth by selectState<InputBirthdayState, String> { month }
    val inputDay by selectState<InputBirthdayState, String> { day }
    val birthday by selectState<InputBirthdayState, ZonedDateTime?> { birthday }
    val dispatch = rememberDispatcher()
    val focusManager = LocalFocusManager.current

    BackHandler(isInitial) {} // 初期時はバックジェスチャをさせない

    LaunchedEffect(Unit) {
        savedBirthday?.let {
            dispatch(InputBirthdayAction.InputYear(it.year.toString()))
            dispatch(InputBirthdayAction.InputMonth(it.monthValue.toString()))
            dispatch(InputBirthdayAction.InputDay(it.dayOfMonth.toString()))
        }
    }

    LaunchedEffect(inputYear, inputMonth, inputDay) {
        dispatch(
            InputBirthdayAction.CheckInput(
                year = inputYear,
                month = inputMonth,
                day = inputDay,
            ),
        )
    }
    Scaffold(
        topBar = {
            if (isInitial.not()) {
                TopBar(backScreen = backScreen)
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .padding(padding)
                .padding(horizontal = 32.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ) {
            if (isInitial) {
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .contentBrush(brush = secondaryBrush()),
                    text = stringResource(id = R.string.welcome),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            TitleCard(
                text = stringResource(id = R.string.register_birth_date),
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
            )
            CustomOutlinedTextField(
                text = inputYear,
                labelText = stringResource(id = R.string.year),
                onValueChange = { year -> dispatch(InputBirthdayAction.InputYear(year)) },
            )
            CustomOutlinedTextField(
                text = inputMonth,
                labelText = stringResource(id = R.string.month),
                onValueChange = { month -> dispatch(InputBirthdayAction.InputMonth(month)) },
            )
            CustomOutlinedTextField(
                modifier = Modifier.padding(bottom = 8.dp),
                text = inputDay,
                labelText = stringResource(id = R.string.day),
                onValueChange = { day -> dispatch(InputBirthdayAction.InputDay(day)) },
            )
            if (isInitial) {
                Text(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.can_be_changed),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Right,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            IconAndTextButton(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 24.dp),
                isClickable = birthday != null,
                painter = painterResource(id = R.drawable.baseline_check_24),
                text = when {
                    isInitial -> stringResource(id = R.string.register)
                    else -> stringResource(id = R.string.change)
                },
                onClick = { birthday?.let { registerNewBirthday(it) } },
            )
        }
    }
}

@Preview
@Composable
fun PreviewInputMyBirthdayContent_Initial_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        StoreProvider(
            store = createStore(
                reducer = inputBirthdayReducer,
                preloadedState = InputBirthdayState(year = "", month = "", day = ""),
            ),
        ) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                InputMyBirthdayContent(isInitial = true, registerNewBirthday = {})
            }
        }
    }
}

@Preview
@Composable
fun PreviewInputMyBirthdayContent_Initial_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        StoreProvider(
            store = createStore(
                reducer = inputBirthdayReducer,
                preloadedState = InputBirthdayState(year = "", month = "", day = ""),
            ),
        ) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                InputMyBirthdayContent(isInitial = true, registerNewBirthday = {})
            }
        }
    }
}

@Preview
@Composable
fun PreviewInputMyBirthdayContent_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        StoreProvider(
            store = createStore(
                reducer = inputBirthdayReducer,
                preloadedState = InputBirthdayState(year = "", month = "", day = ""),
            ),
        ) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                InputMyBirthdayContent(isInitial = false, registerNewBirthday = {})
            }
        }
    }
}

@Preview
@Composable
fun PreviewInputMyBirthdayContent_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        StoreProvider(
            store = createStore(
                reducer = inputBirthdayReducer,
                preloadedState = InputBirthdayState(year = "", month = "", day = ""),
            ),
        ) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                InputMyBirthdayContent(isInitial = false, registerNewBirthday = {})
            }
        }
    }
}
