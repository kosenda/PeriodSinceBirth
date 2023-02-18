package ksnd.periodsincebirth.middleware

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ksnd.periodsincebirth.actions.SettingAction
import ksnd.periodsincebirth.repository.DataStoreRepository
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.TypedStore
import javax.inject.Inject

class SettingMiddleware @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val dataStoreRepository: DataStoreRepository,
) : Middleware<State> {

    override fun invoke(
        store: TypedStore<State, Any>,
    ): (next: Dispatcher) -> (action: Any) -> Any = { next ->
        { action ->
            next(action)
            when (action) {
                is SettingAction.FetchTheme -> {
                    CoroutineScope(ioDispatcher).launch {
                        // TODO 設定されたThemeを読み取る
                    }
                }
                else -> {}
            }
        }
    }
}