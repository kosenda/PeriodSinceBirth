package ksnd.periodsincebirth.ui

sealed class NavigationItems(val route: String) {
    object Loading : NavigationItems(route = "loading")
    object InputBirthday : NavigationItems(route = "input_birthday")
    object PeriodSinceBirth : NavigationItems(route = "period_since_birth")
    object Settings : NavigationItems(route = "settings")
    object Info : NavigationItems(route = "info")
}