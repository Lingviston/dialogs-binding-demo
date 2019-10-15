package by.ve.dialogsbinding.ui.demo.dialog.solution1

import by.ve.dialogsbinding.lifecycle.SingleLiveEvent
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.dialog.base.BaseSolutionViewModel
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEventBus
import io.reactivex.rxkotlin.plusAssign

private const val DIALOG_TAG = "Error"

class Solution1ViewModel(
    service: FirstTryFailingService,
    dialogEventBus: DialogEventBus
) : BaseSolutionViewModel(service) {

    val showErrorDialogEvent = SingleLiveEvent<ShowDialogEvent>()

    val hideDialogEvent = SingleLiveEvent<String>()

    init {
        disposables += dialogEventBus.getEvents(DIALOG_TAG).subscribe { event ->
            when (event) {
                DialogEvent.PositiveButtonClickEvent -> onErrorRetry()
                DialogEvent.NegativeButtonClickEvent -> onErrorCancel()
            }
        }
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
