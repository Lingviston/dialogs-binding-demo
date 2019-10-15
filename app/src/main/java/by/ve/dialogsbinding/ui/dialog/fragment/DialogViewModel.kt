package by.ve.dialogsbinding.ui.dialog.fragment

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.lifecycle.SingleLiveEvent
import by.ve.dialogsbinding.ui.dialog.common.IDialogViewModel


class DialogViewModel : ViewModel(), IDialogViewModel {

    val positiveButtonClickEvent = SingleLiveEvent<Void>()

    val negativeButtonClickEvent = SingleLiveEvent<Void>()

    override fun onPositiveButtonClick() {
        positiveButtonClickEvent.call()
    }

    override fun onNegativeButtonClick() {
        negativeButtonClickEvent.call()
    }
}