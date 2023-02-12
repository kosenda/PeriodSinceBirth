package ksnd.periodsincebirth.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.ZonedDateTime

class TimeUtilTest {
    @Test
    fun timeUtil_isExistDate_isTrue() {
        // 入力された年月日が実在する場合はZonedDateTimeが作成される
        assertThat(makeBirthday(year = "2023", month = "2", day = "11"))
            .isInstanceOf(ZonedDateTime::class.java)
        // 先頭に０があってもZonedDateTimeを作成できる
        assertThat(makeBirthday(year = "0001", month = "01", day = "01"))
            .isInstanceOf(ZonedDateTime::class.java)
    }

    @Test
    fun timeUtil_isNotExistMonth_isFalse() {
        // 入力された値(月)が実在しない場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "2023", month = "00", day = "11")).isNull()
        assertThat(makeBirthday(year = "2023", month = "13", day = "11")).isNull()
    }

    @Test
    fun timeUtil_isNotExistDay_isFalse() {
        // 入力された値(日)が実在しない場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "2023", month = "02", day = "00")).isNull()
        assertThat(makeBirthday(year = "2023", month = "02", day = "32")).isNull()
    }

    @Test
    fun timeUtil_isShortLength_isFalse() {
        // 入力された値の桁数が足りない場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "202", month = "02", day = "11")).isNull()
        assertThat(makeBirthday(year = "2023", month = "", day = "11")).isNull()
        assertThat(makeBirthday(year = "2023", month = "02", day = "")).isNull()
    }

    @Test
    fun timeUtil_isOverLength_isFalse() {
        // 入力された値の桁数が多すぎる場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "20232", month = "02", day = "11")).isNull()
        assertThat(makeBirthday(year = "2023", month = "021", day = "11")).isNull()
        assertThat(makeBirthday(year = "2023", month = "02", day = "111")).isNull()
    }
}