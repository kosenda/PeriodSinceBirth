package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.PreviewStoreProvider
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.parts.SwitchWithText
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun SettingOtherContent() {
    val settingState by selectState<State, SettingState> { settingState }
    val dispatch = rememberDispatcher()
    TitleCard(
        text = stringResource(id = R.string.other_settings),
        painter = painterResource(id = R.drawable.baseline_drag_indicator_24),
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        SwitchWithText(
            text = stringResource(id = R.string.animation_text),
            checked = settingState.useAnimationText,
            onCheckedChange = { useAnimation ->
                dispatch(SettingAction.SwitchAnimationText(useAnimation))
            },
        )
    }
}

@Preview
@Composable
private fun PreviewSettingOtherContent_Light() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = false) {
            Column {
                SettingOtherContent()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingOtherContent_Dark() {
    PreviewStoreProvider {
        PeriodSinceBirthTheme(isDarkTheme = true) {
            Column {
                SettingOtherContent()
            }
        }
    }
}