package ksnd.periodsincebirth.state

data class State(
    val appState: AppState = AppState(),
    val inputDateState: InputDateState = InputDateState(),
    val settingState: SettingState = SettingState(),
)
