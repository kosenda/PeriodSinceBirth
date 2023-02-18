package ksnd.periodsincebirth.repository

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ksnd.periodsincebirth.MainDispatcherRule
import ksnd.periodsincebirth.util.makeBirthday
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class DataStoreRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val dataStoreRepositoryImpl = DataStoreRepositoryImpl(
        dataStore = PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("DataStoreRepositoryImpl") },
        ),
    )

    @Test
    fun dataStoreRepository_initialFetchBirthday_isNull() {
        // 初期値はNullであること
        mainDispatcherRule.testScope.runTest {
            assertThat(dataStoreRepositoryImpl.fetchBirthday()).isNull()
        }
    }

    @Test
    fun dataStoreRepository_updateBirthday_isNotNull() {
        // 更新をした後に取得した値がNullでないこと
        mainDispatcherRule.testScope.runTest {
            val birthday = makeBirthday(year = "2000", month = "1", day = "1")
            dataStoreRepositoryImpl.updateBirthday(newBirthday = birthday.toString())
            assertThat(dataStoreRepositoryImpl.fetchBirthday()).isNotNull()
        }
    }

    @Test
    fun dataStoreRepository_updateThreeTimes_isChange() {
        // ３回更新し、全てで取得できる値が変わっていること
        mainDispatcherRule.testScope.runTest {
            val firstBirthday = makeBirthday(year = "2000", month = "1", day = "1").toString()
            val secondBirthday = makeBirthday(year = "2001", month = "2", day = "2").toString()
            val thirdBirthday = makeBirthday(year = "2000", month = "3", day = "3").toString()
            dataStoreRepositoryImpl.updateBirthday(newBirthday = firstBirthday)
            dataStoreRepositoryImpl.updateBirthday(newBirthday = secondBirthday)
            assertThat(dataStoreRepositoryImpl.fetchBirthday()).isNotEqualTo(firstBirthday)
            assertThat(dataStoreRepositoryImpl.fetchBirthday()).isEqualTo(secondBirthday)
            dataStoreRepositoryImpl.updateBirthday(newBirthday = thirdBirthday)
            assertThat(dataStoreRepositoryImpl.fetchBirthday()).isNotEqualTo(secondBirthday)
            assertThat(dataStoreRepositoryImpl.fetchBirthday()).isEqualTo(thirdBirthday)
        }
    }

}