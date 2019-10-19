package by.ve.dialogsbinding.ui.demo.dialog.solution1

import by.ve.dialogsbinding.lifecycle.SingleLiveEvent
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.dialog.base.BaseSolutionViewModel
import by.ve.dialogsbinding.ui.dialog.common.STANDARD_DIALOG_CONFIG
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

    val dialogControlEvent = SingleLiveEvent<DialogControlEvent>()

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
        dialogControlEvent.value = DialogControlEvent.Show(
            tag = DIALOG_TAG,
            uiConfig = STANDARD_DIALOG_CONFIG
        )
    }

    override fun hideErrorDialog() {
        dialogControlEvent.value = DialogControlEvent.Hide(DIALOG_TAG)
    }
}
