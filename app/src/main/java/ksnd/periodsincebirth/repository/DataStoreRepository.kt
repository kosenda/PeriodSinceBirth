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
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface DataStoreRepository {
    suspend fun fetchBirthday(): String?
    suspend fun updateBirthday(newBirthday: String)
    suspend fun updateTheme(newTheme: Theme)
    suspend fun selectedTheme(): Theme
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
        return when(selectedThemeNum) {
            Theme.NIGHT.num -> Theme.NIGHT
            Theme.LIGHT.num -> Theme.LIGHT
            Theme.AUTO.num -> Theme.AUTO
            else -> {
                Timber.e("不正なテーマの値: $selectedThemeNum")
                Theme.AUTO
            }
        }
    }

    override suspend fun updateTheme(newTheme: Theme) {
        dataStore.edit { it[PreferencesKey.THEME_NUM] = newTheme.num }
    }

}
