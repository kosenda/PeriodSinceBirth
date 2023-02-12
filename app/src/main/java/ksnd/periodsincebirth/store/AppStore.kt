package ksnd.periodsincebirth.store

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ksnd.periodsincebirth.state.AppState
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class AppStore @Inject constructor(
    val store: Store<AppState>,
) : ViewModel()
