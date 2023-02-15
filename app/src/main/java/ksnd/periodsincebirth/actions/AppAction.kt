package ksnd.periodsincebirth.actions

import ksnd.periodsincebirth.ui.NavigationItems
import java.time.ZonedDateTime

sealed interface AppAction {
    data class SetBirthday(val newBirthday: ZonedDateTime) : AppAction
    data class ChangeBirthday(val newBirthday: ZonedDateTime) : AppAction
    data class TransitionScreen(val next: NavigationItems) : AppAction
    object FetchBirthday : AppAction
}
