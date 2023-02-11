package ksnd.periodsincebirth.store

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ksnd.periodsincebirth.state.InputBirthdayState
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class InputBirthdayStore @Inject constructor(
    val store: Store<InputBirthdayState>,
) : ViewModel()
