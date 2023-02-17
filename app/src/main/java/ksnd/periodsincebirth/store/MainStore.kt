package ksnd.periodsincebirth.store

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ksnd.periodsincebirth.state.State
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class MainStore @Inject constructor(
    val store: Store<State>,
) : ViewModel()
