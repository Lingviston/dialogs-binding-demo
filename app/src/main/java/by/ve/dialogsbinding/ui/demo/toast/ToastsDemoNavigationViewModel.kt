package by.ve.dialogsbinding.ui.demo.toast

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.lifecycle.SingleLiveEvent

class ToastsDemoNavigationViewModel : ViewModel() {

    val showAnotherFragmentEvent = SingleLiveEvent<Unit>()

    val backEvent = SingleLiveEvent<Unit>()

    fun onShowAnotherFragmentClick() {
        showAnotherFragmentEvent.call()
    }

    fun onBackClick() {
        backEvent.call()
    }
}