package ksnd.periodsincebirth.middleware

import ksnd.periodsincebirth.actions.InputBirthdayAction
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.util.makeBirthday
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.TypedStore
import timber.log.Timber

class InputBirthdayMiddleware : Middleware<State> {

    override fun invoke(
        store: TypedStore<State, Any>,
    ): (next: Dispatcher) -> (action: Any) -> Any = { next ->
        { action ->
            next(action)
            when (action) {
                is InputBirthdayAction.CheckInput -> {
                    val birthday = makeBirthday(action.year, action.month, action.day)
                    next(InputBirthdayAction.SetBirthday(birthday = birthday))
                    Timber.i(birthday.toString())
                }
                else -> {}
            }
        }
    }
}
