package ksnd.periodsincebirth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ksnd.periodsincebirth.middleware.AppMiddleware
import ksnd.periodsincebirth.middleware.InputBirthdayMiddleware
import ksnd.periodsincebirth.reducer.appReducer
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.repository.DataStoreRepositoryImpl
import ksnd.periodsincebirth.state.AppState
import ksnd.periodsincebirth.state.InputBirthdayState
import ksnd.periodsincebirth.ui.NavigationItems
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
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
    ): Store<AppState> {
        return createStore(
            reducer = appReducer,
            preloadedState = AppState(birthday = null, navState = NavigationItems.PeriodSinceBirth),
            enhancer = applyMiddleware(
                AppMiddleware(
                    ioDispatcher = ioDispatcher,
                    dataStoreRepository = dataStoreRepository,
                ),
            ),
        )
    }

    @Provides
    @Singleton
    fun provideInputBirthdayStore(): Store<InputBirthdayState> {
        return createStore(
            reducer = inputBirthdayReducer,
            preloadedState = InputBirthdayState(year = "", month = "", day = ""),
            enhancer = applyMiddleware(
                InputBirthdayMiddleware(),
            ),
        )
    }
}
