package ksnd.periodsincebirth.middleware

import ksnd.periodsincebirth.actions.InputDateAction
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.util.makeBirthday
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.TypedStore
import timber.log.Timber

class InputDateMiddleware : Middleware<State> {

    override fun invoke(
        store: TypedStore<State, Any>,
    ): (next: Dispatcher) -> (action: Any) -> Any = { next ->
        { action ->
            next(action)
            when (action) {
                is InputDateAction.CheckInput -> {
                    val birthday = makeBirthday(action.year, action.month, action.day)
                    next(InputDateAction.SetBirthday(date = birthday))
                    Timber.i("入力された生年月日: %s".format(birthday.toString()))
                }
                else -> {}
            }
        }
    }
}
