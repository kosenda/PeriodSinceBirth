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
                        val selectedTheme = dataStoreRepository.selectedTheme()
                        next(SettingAction.SetTheme(selectedTheme))
                    }
                }
                is SettingAction.ChangeTheme -> {
                    CoroutineScope(ioDispatcher).launch {
                        dataStoreRepository.updateTheme(newTheme = action.theme)
                    }
                }
                is SettingAction.FetchUseAnimationText -> {
                    CoroutineScope(ioDispatcher).launch {
                        val useAnimationText = dataStoreRepository.fetchUseAnimationText()
                        next(SettingAction.SetUseAnimationText(useAnimationText))
                    }
                }
                is SettingAction.SwitchAnimationText -> {
                    CoroutineScope(ioDispatcher).launch {
                        dataStoreRepository.updateUseAnimationText(useAnimation = action.useAnimate)
                    }
                }
                else -> {}
            }
        }
    }
}
