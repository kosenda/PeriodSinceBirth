package ksnd.periodsincebirth

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKey {
    val BIRTHDAY = stringPreferencesKey("birthday")
    val THEME_NUM = intPreferencesKey("theme_num")
    val USE_ANIMATION_TEXT = booleanPreferencesKey("use_animation_text")
    val FONT_TYPE = stringPreferencesKey("font_type")
}
