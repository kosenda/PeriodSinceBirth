package ksnd.periodsincebirth.middleware

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ksnd.periodsincebirth.actions.AppAction
import ksnd.periodsincebirth.repository.DataStoreRepository
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.TypedStore
import timber.log.Timber
import java.time.ZonedDateTime
import javax.inject.Inject

class AppMiddleware @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val dataStoreRepository: DataStoreRepository,
) : Middleware<AppState> {

    override fun invoke(
        store: TypedStore<AppState, Any>,
    ): (next: Dispatcher) -> (action: Any) -> Any = { next ->
        { action ->
            next(action)
            when (action) {
                is AppAction.FetchBirthday -> {
                    CoroutineScope(ioDispatcher).launch {
                        val birthday: String? = dataStoreRepository.fetchBirthday()
                        Timber.i(birthday)
                        when {
                            birthday != null -> {
                                next(AppAction.ChangeBirthday(ZonedDateTime.parse(birthday)))
                                next(AppAction.TransitionScreen(NavigationItems.PeriodSinceBirth))
                            }
                            else -> {
                                next(AppAction.TransitionScreen(NavigationItems.InputBirthday))
                            }
                        }
                    }
                }
                is AppAction.ChangeBirthday -> {
                    CoroutineScope(ioDispatcher).launch {
                        dataStoreRepository.updateBirthday(action.newBirthday.toString())
                    }
                }
                else -> {}
            }
        }
    }
}
