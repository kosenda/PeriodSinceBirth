package ksnd.periodsincebirth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.reducer.mainReducer
import ksnd.periodsincebirth.state.InputBirthdayState
import ksnd.periodsincebirth.state.MainState
import org.reduxkotlin.Store
import org.reduxkotlin.createStore
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InputBirthdayStore

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {
    @Provides
    @Singleton
    fun provideMainStore(): Store<MainState> {
        return createStore(
            reducer = mainReducer,
            preloadedState = MainState(myBirthday = null, showSettingDialog = false),
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
