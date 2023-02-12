package ksnd.periodsincebirth.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class TimeUtilTest {

    @Test
    fun makeBirth_isExistDate_isMadeDate() {
        // 入力された年月日が実在する場合はZonedDateTimeが作成される
        assertThat(makeBirthday(year = "2021", month = "2", day = "11"))
            .isInstanceOf(ZonedDateTime::class.java)
        // 先頭に０があってもZonedDateTimeを作成できる
        assertThat(makeBirthday(year = "0001", month = "01", day = "01"))
            .isInstanceOf(ZonedDateTime::class.java)
    }

    @Test
    fun makeBirth_isNotExistMonth_isNull() {
        // 入力された値(月)が実在しない場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "2021", month = "00", day = "11")).isNull()
        assertThat(makeBirthday(year = "2021", month = "13", day = "11")).isNull()
    }

    @Test
    fun makeBirth_isNotExistDay_isNull() {
        // 入力された値(日)が実在しない場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "2021", month = "02", day = "00")).isNull()
        assertThat(makeBirthday(year = "2021", month = "02", day = "32")).isNull()
    }

    @Test
    fun makeBirth_isShortLength_isNull() {
        // 入力された値の桁数が足りない場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "202", month = "02", day = "11")).isNull()
        assertThat(makeBirthday(year = "2021", month = "", day = "11")).isNull()
        assertThat(makeBirthday(year = "2021", month = "02", day = "")).isNull()
    }

    @Test
    fun makeBirth_inputIsAfterNow_isNull() {
        // 入力された年月日が今よりも未来な場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "3000", month = "01", day = "01")).isNull()
    }

    @Test
    fun makeBirth_isOverLength_isFalse() {
        // 入力された値の桁数が多すぎる場合はZonedDateTimeが作成されない
        assertThat(makeBirthday(year = "20212", month = "02", day = "11")).isNull()
        assertThat(makeBirthday(year = "2021", month = "021", day = "11")).isNull()
        assertThat(makeBirthday(year = "2021", month = "02", day = "111")).isNull()
    }

    @Test
    fun convertZoneTimeToStr_zonedDateTime_yyyyMMdd() {
        // ZonedDateTimeを渡すとyyyy/MM/ddの形式で文字列を作成する
        val regex = "\\d{4}/\\d{2}/\\d{2}"
        assertThat(convertZoneTimeToStr(ZonedDateTime.now())).matches(regex)
        assertThat(
            convertZoneTimeToStr(
                makeBirthday(year = "0001", month = "01", day = "01")!!,
            ),
        ).matches(regex)
    }

    @Test
    fun untilNow_chronoUnit_isReturn() {
        // 指定した日時とchronoUnitからの経過数を取得できる
        val time = makeBirthday(year = "0001", month = "01", day = "01")!!
        assertThat(untilNow(time = time, chronoUnit = ChronoUnit.YEARS)).isGreaterThan(0L)

        val seconds = untilNow(time, ChronoUnit.SECONDS)
        val minutes = untilNow(time, ChronoUnit.MINUTES)
        val hours = untilNow(time, ChronoUnit.HOURS)
        val days = untilNow(time, ChronoUnit.DAYS)
        val months = untilNow(time, ChronoUnit.MONTHS)
        val years = untilNow(time, ChronoUnit.YEARS)
        assertThat(seconds > minutes).isTrue()
        assertThat(minutes > hours).isTrue()
        assertThat(hours > days).isTrue()
        assertThat(days > months).isTrue()
        assertThat(months > years).isTrue()
    }
}
