package ksnd.periodsincebirth.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ksnd.periodsincebirth.PreferencesKey
import timber.log.Timber
import javax.inject.Inject

interface DataStoreRepository {
    suspend fun fetchBirthday(): String?
    suspend fun updateBirthday(newBirthday: String)
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
}
