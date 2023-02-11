package ksnd.periodsincebirth.state

import java.util.Calendar

data class MainState(
    val myBirthday: Calendar?,
    val showSettingDialog: Boolean,
)
