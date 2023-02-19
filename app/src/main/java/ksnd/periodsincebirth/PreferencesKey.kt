package ksnd.periodsincebirth

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKey {
    val BIRTHDAY = stringPreferencesKey("birthday")
    val THEME_NUM = intPreferencesKey("theme_num")
}
