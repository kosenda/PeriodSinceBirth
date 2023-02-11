package ksnd.periodsincebirth.state

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class InputBirthdayStateTest {

    @Test
    fun inputBirthdayState_isExistDate_isTrue() {
        // 入力された年月日が実在する場合はtrue
        assertThat(
            InputBirthdayState(year = "2023", month = "02", day = "11").isCheckOK()
        ).isEqualTo(true)
    }

    @Test
    fun inputBirthdayState_isNotExistMonth_isFalse() {
        // 入力された値(月)が実在しない場合はFalse
        assertThat(
            InputBirthdayState(year = "2023", month = "00", day = "11").isCheckOK()
        ).isEqualTo(false)
        assertThat(
            InputBirthdayState(year = "2023", month = "13", day = "11").isCheckOK()
        ).isEqualTo(false)
    }

    @Test
    fun inputBirthdayState_isNotExistDay_isFalse() {
        // 入力された値(日)が実在しない場合はFalse
        assertThat(
            InputBirthdayState(year = "2023", month = "02", day = "00").isCheckOK()
        ).isEqualTo(false)
        assertThat(
            InputBirthdayState(year = "2023", month = "02", day = "32").isCheckOK()
        ).isEqualTo(false)
    }

    @Test
    fun inputBirthdayState_isShortLength_isFalse() {
        // 入力された値の桁数が足りない場合はFalse
        assertThat(
            InputBirthdayState(year = "202", month = "02", day = "11").isCheckOK()
        ).isEqualTo(false)
        assertThat(
            InputBirthdayState(year = "2023", month = "", day = "11").isCheckOK()
        ).isEqualTo(false)
        assertThat(
            InputBirthdayState(year = "2023", month = "02", day = "").isCheckOK()
        ).isEqualTo(false)
    }

    @Test
    fun inputBirthdayState_isOverLength_isFalse() {
        // 入力された値の桁数が足りない場合はFalse
        assertThat(
            InputBirthdayState(year = "20232", month = "02", day = "11").isCheckOK()
        ).isEqualTo(false)
        assertThat(
            InputBirthdayState(year = "2023", month = "021", day = "11").isCheckOK()
        ).isEqualTo(false)
        assertThat(
            InputBirthdayState(year = "2023", month = "02", day = "111").isCheckOK()
        ).isEqualTo(false)
    }
}