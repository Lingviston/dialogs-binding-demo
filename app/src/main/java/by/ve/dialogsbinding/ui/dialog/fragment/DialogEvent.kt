package by.ve.dialogsbinding.ui.dialog.fragment


sealed class DialogEvent(private val dialogTag: String) {

    fun doIfTagMatches(expectedTag: String, action: () -> Unit) {
        if (expectedTag == dialogTag) {
            action.invoke()
        }
    }

    class PositiveButtonClickEvent(dialogTag: String) : DialogEvent(dialogTag)

    class NegativeButtonClickEvent(dialogTag: String) : DialogEvent(dialogTag)
}