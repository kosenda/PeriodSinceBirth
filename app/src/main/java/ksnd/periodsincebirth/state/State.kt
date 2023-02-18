package ksnd.periodsincebirth.state

data class State(
    val appState: AppState,
    val inputBirthdayState: InputBirthdayState,
    val settingState: SettingState,
)
