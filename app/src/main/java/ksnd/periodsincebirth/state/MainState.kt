package ksnd.periodsincebirth.state

import java.time.ZonedDateTime

data class MainState(
    val myBirthday: ZonedDateTime?,
    val showSettingDialog: Boolean,
)
