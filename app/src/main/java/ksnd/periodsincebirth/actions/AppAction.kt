package ksnd.periodsincebirth.actions

import ksnd.periodsincebirth.ui.NavigationItems
import java.time.ZonedDateTime

sealed interface AppAction {
    data class ChangeBirthday(val newBirthday: ZonedDateTime) : AppAction
    data class TransitionScreen(val next: NavigationItems) : AppAction
}
