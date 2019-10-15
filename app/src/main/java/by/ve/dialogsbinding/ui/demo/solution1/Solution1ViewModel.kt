package by.ve.dialogsbinding.ui.demo.solution1

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.lifecycle.SingleLiveEvent
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEventBus
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class Solution1ViewModel(
    private val service: FirstTryFailingService,
    dialogEventBus: DialogEventBus
) : ViewModel() {

    val showErrorDialogEvent = SingleLiveEvent<ShowDialogEvent>()

    val hideDialogEvent = SingleLiveEvent<String>()

    private val disposables = CompositeDisposable()

    init {
        doRequest()
        disposables += dialogEventBus.getEvents("Error").subscribe { event ->
            when (event) {
                DialogEvent.PositiveButtonClickEvent -> {
                    hideDialogEvent.value = "Error"
                    doRequest()
                }
                DialogEvent.NegativeButtonClickEvent -> hideDialogEvent.value = "Error"
            }
        }
    }

    private fun doRequest() {
        disposables += service.request()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = ::showErrorDialog
            )
    }

    private fun showErrorDialog(error: Throwable) {
        showErrorDialogEvent.value = ShowDialogEvent(
            tag = "Error",
            uiConfig = DialogUiConfig(
                title = "Error",
                message = "Request failed",
                positiveButtonText = "Retry",
                negativeButtonText = "Cancel"
            )
        )
    }

    override fun onCleared() {
        disposables.dispose()
    }
}
