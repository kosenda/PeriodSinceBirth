package ksnd.periodsincebirth.middleware

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.Middleware
import timber.log.Timber

val appMiddleware : Middleware<AppState> =  { _ ->
    { next ->
        { action ->
            Timber.i("action: %s".format(action))
            next(action)
            CoroutineScope(Dispatchers.IO).launch {
                delay(1000L)
                // TODO DataStore読み込み
//                next(AppAction.ChangeBirthday(makeBirthday("2000", "1", "1")!!))
//                next(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                next(AppAction.TransitionScreen(NavigationItems.InputBirthday))
            }
        }
    }
}