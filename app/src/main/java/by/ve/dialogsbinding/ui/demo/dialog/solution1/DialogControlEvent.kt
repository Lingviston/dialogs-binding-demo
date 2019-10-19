package by.ve.dialogsbinding.ui.demo.dialog.solution1

import by.ve.dialogsbinding.ui.dialog.common.IDialogUiConfig

sealed class DialogControlEvent {

    data class Show(val tag: String, val uiConfig: IDialogUiConfig) : DialogControlEvent()

    data class Hide(val tag: String) : DialogControlEvent()
}