package by.ve.dialogsbinding.ui.dialog.fragment


sealed class DialogEvent {

    object PositiveButtonClickEvent : DialogEvent()

    object NegativeButtonClickEvent : DialogEvent()
}