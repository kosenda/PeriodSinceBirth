package ksnd.periodsincebirth.ui.content

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import ksnd.periodsincebirth.R
import ksnd.periodsincebirth.ui.parts.CustomTextButton
import ksnd.periodsincebirth.ui.parts.TitleCard
import ksnd.periodsincebirth.ui.theme.PeriodSinceBirthTheme

@Composable
fun LicensesContent() {
    val context = LocalContext.current
    val buttonText = stringResource(id = R.string.oss_licenses)
    TitleCard(
        text = stringResource(id = R.string.licenses_title),
        painter = painterResource(id = R.drawable.outline_info_24),
    )
    CustomTextButton(
        text = buttonText,
        onClick = {
            val intent = Intent(context, OssLicensesMenuActivity::class.java)
            intent.putExtra("title", buttonText)
            ContextCompat.startActivity(context, intent, null)
        },
    )
}

@Preview
@Composable
private fun PreviewLicensedContent_Light() {
    PeriodSinceBirthTheme(isDarkTheme = false) {
        LicensesContent()
    }
}

@Preview
@Composable
private fun PreviewLicensedContent_Dark() {
    PeriodSinceBirthTheme(isDarkTheme = true) {
        LicensesContent()
    }
}