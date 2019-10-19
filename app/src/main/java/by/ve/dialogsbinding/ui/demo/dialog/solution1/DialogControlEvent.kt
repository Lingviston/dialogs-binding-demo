package by.ve.dialogsbinding.ui.demo.dialog.solution1

import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig

sealed class DialogControlEvent {

    data class Show(val tag: String, val uiConfig: DialogUiConfig) : DialogControlEvent()

    data class Hide(val tag: String) : DialogControlEvent()
}