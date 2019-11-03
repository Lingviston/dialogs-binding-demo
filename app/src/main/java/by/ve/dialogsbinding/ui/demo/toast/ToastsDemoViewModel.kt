package by.ve.dialogsbinding.ui.demo.toast

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.lifecycle.SingleLiveEvent
import by.ve.dialogsbinding.ui.toast.ToastDisplaySignal
import by.ve.dialogsbinding.ui.toast.ToastViewModel
import com.vmn.playplex.arch.DatabindingSingleLiveEvent

class ToastsDemoViewModel : ViewModel() {

    val showToastBrokenEvent = SingleLiveEvent<ToastDisplaySignal>()

    val showToastFixedEvent = DatabindingSingleLiveEvent<ToastDisplaySignal>()

    val toastBrokenViewModel = ToastViewModel(
        icon = R.mipmap.ic_launcher_round,
        text = R.string.toast_broken_navigation
    )

    val toastFixedViewModel = ToastViewModel(
        icon = R.mipmap.ic_launcher_round,
        text = R.string.toast_fixed_navigation
    )

    fun onShowToastBrokenClick() {
        showToastBrokenEvent.value = ToastDisplaySignal
    }

    fun onShowToastFixedClick() {
        showToastFixedEvent.value = ToastDisplaySignal
    }
}