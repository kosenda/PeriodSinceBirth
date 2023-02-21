package ksnd.periodsincebirth.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ksnd.periodsincebirth.ui.content.SelectLanguageDialogContent

@Composable
fun SelectLanguageDialog() {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        SelectLanguageDialogContent()
    }
}
