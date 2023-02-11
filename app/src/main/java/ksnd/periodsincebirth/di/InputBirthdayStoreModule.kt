package ksnd.periodsincebirth.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ksnd.periodsincebirth.reducer.inputBirthdayReducer
import ksnd.periodsincebirth.state.InputBirthdayState
import org.reduxkotlin.Store
import org.reduxkotlin.createStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InputBirthdayStoreModule {
    @Provides
    @Singleton
    fun provideInputBirthdayStore(): Store<InputBirthdayState> {
        return createStore(
            reducer = inputBirthdayReducer,
            preloadedState = InputBirthdayState("", "", "", false),
        )
    }
}