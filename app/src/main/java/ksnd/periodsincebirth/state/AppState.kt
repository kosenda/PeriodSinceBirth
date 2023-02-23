package ksnd.periodsincebirth.state

import ksnd.periodsincebirth.ui.NavigationItems
import java.time.ZonedDateTime

data class AppState(
    val birthday: ZonedDateTime? = null,
    val navState: NavigationItems = NavigationItems.PeriodSinceBirth,
)
