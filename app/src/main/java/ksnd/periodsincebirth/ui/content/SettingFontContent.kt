package ksnd.periodsincebirth.ui.content

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.FontType
import ksnd.periodsincebirth.ui.parts.CustomRadioButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun SettingFontContent() {
    val settingState by selectState<State, SettingState> { settingState }
    val dispatch = rememberDispatcher()
    val fonts = listOf(
        FontType.DEFAULT,
        FontType.ROCKN_ROLL_ONE,
        FontType.ROBOTO_SLAB,
        FontType.PACIFICO,
        FontType.HACHI_MARU_POP,
    )

    TitleCard(
        text = stringResource(id = R.string.font_setting),
        painter = painterResource(id = R.drawable.baseline_text_fields_24),
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        fonts.map { fontType ->
            CustomRadioButton(
                isSelected = fontType == settingState.fontType,
                buttonText = fontType.fontName,
                onClick = { dispatch(SettingAction.ChangeFont(fontType = fontType)) },
            )
        }
    }
}
