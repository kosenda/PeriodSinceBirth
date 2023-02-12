package ksnd.periodsincebirth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.reducer.mainReducer
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
    fun provideMainStore(): Store<AppState> {
        return createStore(
            reducer = mainReducer,
            preloadedState = AppState(myBirthday = null, navState = NavigationItems.Loading),
            enhancer = applyMiddleware(), // TODO
        )
    }

    @Provides
    @Singleton
    fun provideInputBirthdayStore(): Store<InputBirthdayState> {
        return createStore(
            reducer = inputBirthdayReducer,
            preloadedState = InputBirthdayState(year = "", month = "", day = ""),
        )
    }
}
