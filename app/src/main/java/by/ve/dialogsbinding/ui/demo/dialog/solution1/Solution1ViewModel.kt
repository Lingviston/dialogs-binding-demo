package by.ve.dialogsbinding.ui.demo.dialog.solution1

import by.ve.dialogsbinding.lifecycle.SingleLiveEvent
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.dialog.base.BaseSolutionViewModel
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent.NegativeButtonClickEvent
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent.PositiveButtonClickEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

private const val DIALOG_TAG = "Error"

class Solution1ViewModel(
    service: FirstTryFailingService,
    private val dialogEventBus: EventBus
) : BaseSolutionViewModel(service) {

    val showErrorDialogEvent = SingleLiveEvent<ShowDialogEvent>()

    val hideDialogEvent = SingleLiveEvent<String>()

    init {
        dialogEventBus.register(this)
    }

    override fun onCleared() {
        dialogEventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPositiveButtonClick(event: PositiveButtonClickEvent) {
        event.doIfTagMatches(DIALOG_TAG, ::onErrorRetry)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNegativeButtonClick(event: NegativeButtonClickEvent) {
        event.doIfTagMatches(DIALOG_TAG, ::onErrorCancel)
    }

    override fun showErrorDialog() {
        showErrorDialogEvent.value = ShowDialogEvent(
            tag = DIALOG_TAG,
            uiConfig = DialogUiConfig(
                title = "Error",
                message = "Request failed",
                positiveButtonText = "Retry",
                negativeButtonText = "Cancel"
            )
        )
    }

    override fun hideErrorDialog() {
        hideDialogEvent.value = DIALOG_TAG
    }
}
