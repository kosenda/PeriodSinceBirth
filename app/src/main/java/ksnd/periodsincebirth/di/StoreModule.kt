package ksnd.periodsincebirth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ksnd.periodsincebirth.Theme
import ksnd.periodsincebirth.middleware.AppMiddleware
import ksnd.periodsincebirth.middleware.InputDateMiddleware
import ksnd.periodsincebirth.middleware.SettingMiddleware
import ksnd.periodsincebirth.reducer.appReducer
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.reducer.settingReducer
import ksnd.periodsincebirth.repository.DataStoreRepositoryImpl
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.state.InputDateState
import ksnd.periodsincebirth.state.SettingState
import ksnd.periodsincebirth.state.State
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.combineReducers
import org.reduxkotlin.createStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {
    @Provides
    @Singleton
    fun provideAppStore(
        @IODispatcher ioDispatcher: CoroutineDispatcher,
        dataStoreRepository: DataStoreRepositoryImpl,
    ): Store<State> {
        return createStore(
            reducer = combineReducers(
                appReducer,
                inputBirthdayReducer,
                settingReducer,
            ),
            preloadedState = State(
                appState = AppState(birthday = null, navState = NavigationItems.PeriodSinceBirth),
                inputDateState = InputDateState(year = "", month = "", day = ""),
                settingState = SettingState(theme = Theme.AUTO, locale = ""),
            ),
            enhancer = applyMiddleware(
                AppMiddleware(
                    ioDispatcher = ioDispatcher,
                    dataStoreRepository = dataStoreRepository,
                ),
                InputDateMiddleware(),
                SettingMiddleware(
                    ioDispatcher = ioDispatcher,
                    dataStoreRepository = dataStoreRepository,
                ),
            ),
        )
    }
}
