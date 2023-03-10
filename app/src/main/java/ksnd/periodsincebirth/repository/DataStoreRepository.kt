package ksnd.periodsincebirth.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ksnd.periodsincebirth.PreferencesKey
import ksnd.periodsincebirth.Theme
import ksnd.periodsincebirth.ui.FontType
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface DataStoreRepository {
    suspend fun fetchBirthday(): String?
    suspend fun updateBirthday(newBirthday: String)
    suspend fun updateTheme(newTheme: Theme)
    suspend fun selectedTheme(): Theme
    suspend fun fetchUseAnimationText(): Boolean
    suspend fun updateUseAnimationText(useAnimation: Boolean)
    suspend fun fetchFontType(): FontType
    suspend fun updateFontType(newFontType: FontType)
}

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStoreRepository {

    override suspend fun fetchBirthday(): String? {
        return dataStore
            .data
            .catch { exception ->
                Timber.i(exception)
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKey.BIRTHDAY]
            }
            .first()
    }

    override suspend fun updateBirthday(newBirthday: String) {
        dataStore.edit { it[PreferencesKey.BIRTHDAY] = newBirthday }
    }

    override suspend fun selectedTheme(): Theme {
        val selectedThemeNum = dataStore
            .data
            .catch { exception ->
                Timber.e("DataStore: %s", exception)
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> Theme.AUTO.num
                }
            }
            .map { preferences ->
                preferences[PreferencesKey.THEME_NUM] ?: Theme.AUTO.num
            }
            .first()
        return when (selectedThemeNum) {
            Theme.NIGHT.num -> Theme.NIGHT
            Theme.LIGHT.num -> Theme.LIGHT
            Theme.AUTO.num -> Theme.AUTO
            else -> {
                Timber.e("????????????????????????: $selectedThemeNum")
                Theme.AUTO
            }
        }
    }

    override suspend fun fetchUseAnimationText(): Boolean {
        val useAnimationText = dataStore
            .data
            .catch { exception ->
                Timber.i(exception)
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKey.USE_ANIMATION_TEXT]
            }
            .first()
        return when (useAnimationText) {
            null -> true
            else -> useAnimationText
        }
    }

    override suspend fun updateUseAnimationText(useAnimation: Boolean) {
        dataStore.edit { it[PreferencesKey.USE_ANIMATION_TEXT] = useAnimation }
    }

    override suspend fun updateTheme(newTheme: Theme) {
        dataStore.edit { it[PreferencesKey.THEME_NUM] = newTheme.num }
    }

    override suspend fun fetchFontType(): FontType {
        val fontType = dataStore
            .data
            .catch { exception ->
                Timber.i(exception)
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKey.FONT_TYPE]
            }
            .first()
        return when (fontType) {
            FontType.DEFAULT.fontName -> FontType.DEFAULT
            FontType.ROCKN_ROLL_ONE.fontName -> FontType.ROCKN_ROLL_ONE
            FontType.ROBOTO_SLAB.fontName -> FontType.ROBOTO_SLAB
            FontType.PACIFICO.fontName -> FontType.PACIFICO
            FontType.HACHI_MARU_POP.fontName -> FontType.HACHI_MARU_POP
            else -> {
                Timber.e("????????????????????????????????????: $fontType")
                FontType.DEFAULT
            }
        }
    }

    override suspend fun updateFontType(newFontType: FontType) {
        dataStore.edit { it[PreferencesKey.FONT_TYPE] = newFontType.fontName }
    }
}
