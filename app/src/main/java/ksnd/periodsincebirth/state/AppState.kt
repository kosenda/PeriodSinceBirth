package ksnd.periodsincebirth.state

import ksnd.periodsincebirth.ui.NavigationItems
import java.time.ZonedDateTime

data class AppState(
    val myBirthday: ZonedDateTime?,
    val navState: NavigationItems,
)
