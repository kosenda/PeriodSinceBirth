package ksnd.periodsincebirth.middleware

import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.Middleware
import timber.log.Timber

fun appMiddleware() : Middleware<AppState> =  { _ ->
    { next ->
        { action ->
            Timber.i("next前")
            next(action)
            if (action is AppAction.ChangeBirthday) {
                AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth)
                Timber.i("Transitionした")
            }
            Timber.i("next後")
        }
    }
}