package by.ve.dialogsbinding.ui.demo.dialog.solution3

import by.ve.dialogsbinding.ui.dialog.common.IDialogViewModel


class DialogViewModel(
    private val positiveClick: (() -> Unit)? = null,
    private val negativeClick: (() -> Unit)? = null
) : IDialogViewModel {

    override fun onPositiveButtonClick() {
        positiveClick?.invoke()
    }

    override fun onNegativeButtonClick() {
        negativeClick?.invoke()
    }
}