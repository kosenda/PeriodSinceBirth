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
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.actions.InputDateAction
import ksnd.periodsincebirth.state.InputDateState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.NavigationItems
import ksnd.periodsincebirth.ui.parts.CustomOutlinedTextField
import ksnd.periodsincebirth.ui.parts.IconAndTextButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.parts.TopBar
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import ksnd.periodsincebirth.ui.theme.contentBrush
import ksnd.periodsincebirth.ui.theme.secondaryBrush
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBirthdayContent(
    isInitial: Boolean,
    savedBirthday: ZonedDateTime? = null,
) {
    val inputBirthdayState by selectState<State, InputDateState> { inputDateState }
    val dispatch = rememberDispatcher()
    val focusManager = LocalFocusManager.current

    BackHandler(isInitial) {} // 初期時のみ、戻るジェスチャーを無効にする

    LaunchedEffect(Unit) {
        savedBirthday?.let {
            dispatch(InputDateAction.InputYear(it.year.toString()))
            dispatch(InputDateAction.InputMonth(it.monthValue.toString()))
            dispatch(InputDateAction.InputDay(it.dayOfMonth.toString()))
        }
    }

    LaunchedEffect(inputBirthdayState.year, inputBirthdayState.month, inputBirthdayState.day) {
        dispatch(
            InputDateAction.CheckInput(
                year = inputBirthdayState.year,
                month = inputBirthdayState.month,
                day = inputBirthdayState.day,
            ),
        )
    }
    Scaffold(
        topBar = {
            if (isInitial.not()) {
                TopBar(
                    backScreen = {
                        dispatch(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                    },
                )
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
                text = inputBirthdayState.year,
                labelText = stringResource(id = R.string.year),
                onValueChange = { year -> dispatch(InputDateAction.InputYear(year)) },
            )
            CustomOutlinedTextField(
                text = inputBirthdayState.month,
                labelText = stringResource(id = R.string.month),
                onValueChange = { month -> dispatch(InputDateAction.InputMonth(month)) },
            )
            CustomOutlinedTextField(
                modifier = Modifier.padding(bottom = 8.dp),
                text = inputBirthdayState.day,
                labelText = stringResource(id = R.string.day),
                onValueChange = { day -> dispatch(InputDateAction.InputDay(day)) },
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
                isClickable = inputBirthdayState.birthday != null,
                painter = painterResource(id = R.drawable.baseline_check_24),
                text = when {
                    isInitial -> stringResource(id = R.string.register)
                    else -> stringResource(id = R.string.change)
                },
                onClick = {
                    inputBirthdayState.birthday?.let {
                        dispatch(AppAction.ChangeBirthday(it))
                        dispatch(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                    } 
                },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewInputBirthdayContent_Initial_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                InputBirthdayContent(isInitial = true)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewInputBirthdayContent_Initial_Dark() {
    PreviewStoreProvider {
        Surface(color = MaterialTheme.colorScheme.surface) {
            InputBirthdayContent(isInitial = true)
        }
    }
}

@Preview
@Composable
private fun PreviewInputBirthdayContent_Light() {
    PreviewStoreProvider {
        Surface(color = MaterialTheme.colorScheme.surface) {
            InputBirthdayContent(isInitial = false)
        }
    }
}

@Preview
@Composable
private fun PreviewInputBirthdayContent_Dark() {
    PreviewStoreProvider {
        Surface(color = MaterialTheme.colorScheme.surface) {
            InputBirthdayContent(isInitial = true)
        }
    }
}
