package ksnd.periodsincebirth.actions

import android.content.Context
import ksnd.periodsincebirth.ui.NavigationItems
import java.time.ZonedDateTime

sealed interface AppAction {
    data class SetBirthday(val newBirthday: ZonedDateTime) : AppAction
    data class ChangeBirthday(val newBirthday: ZonedDateTime) : AppAction
    data class TransitionScreen(val next: NavigationItems) : AppAction
    data class FetchBirthday(val context: Context) : AppAction
}
