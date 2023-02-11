package ksnd.periodsincebirth.ui

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.reducer.mainReducer
import ksnd.periodsincebirth.state.InputBirthdayState
import ksnd.periodsincebirth.state.MainState
import ksnd.periodsincebirth.store.InputBirthdayStore
import ksnd.periodsincebirth.store.MainStore
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState
import org.reduxkotlin.createStore
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainStore: MainStore = hiltViewModel()
            PeriodSinceBirthTheme {
                StoreProvider(
                    store = mainStore.store
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(inputBirthdayStore: InputBirthdayStore = hiltViewModel()) {
    val myBirthday by selectState<MainState, Calendar?> { myBirthday }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputMyBirthdayContent() {
    val inputYear by selectState<InputBirthdayState, String> { year }
    val inputMonth by selectState<InputBirthdayState, String> { month }
    val inputDay by selectState<InputBirthdayState, String> { day }
    val isChangeable by selectState<InputBirthdayState, Boolean> { isChangeable }
    val dispatch = rememberDispatcher()
    Column {
        OutlinedTextField(
            value = inputYear,
            onValueChange = { year -> dispatch(InputBirthdayAction.InputYear(year)) }
        )
        OutlinedTextField(
            value = inputMonth,
            onValueChange = { month -> dispatch(InputBirthdayAction.InputMonth(month)) }
        )
        OutlinedTextField(
            value = inputDay,
            onValueChange = { day -> dispatch(InputBirthdayAction.InputDay(day)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier,
    text: String,
    unit: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary
        ),
        onValueChange = onValueChange,
        trailingIcon = {
            Text(
                text = unit,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    )
}

@Composable
fun PeriodSinceBirthContent(myBirthday: Calendar) {
}

fun convertStringFromCalendar(calendar: Calendar?): String {
    return if (calendar == null) {
        " - "
    } else {
        DateFormat.format(
            "YYYY/MM/DD",
            calendar,
        ).toString()
    }
}