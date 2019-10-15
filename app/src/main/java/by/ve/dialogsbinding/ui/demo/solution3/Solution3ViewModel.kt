package by.ve.dialogsbinding.ui.demo.solution3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy


class Solution3ViewModel(
    private val service: FirstTryFailingService
) : ViewModel() {

    val isDialogVisible = MutableLiveData<Boolean>()

    val errorDialogUiConfig = DialogUiConfig(
        title = "Error",
        message = "Request failed",
        positiveButtonText = "Retry",
        negativeButtonText = "Cancel"
    )

    val errorDialogConfig = DialogUiConfig(
        title = "Error",
        message = "Request failed",
        positiveButtonText = "Retry",
        negativeButtonText = "Cancel"
    )

    val errorDialogViewModel = DialogViewModel(
        positiveClick = ::onErrorRetry,
        negativeClick = ::onErrorCancel
    )

    private val disposables = CompositeDisposable()

    init {
        doRequest()
    }

    fun onErrorCancel() {
        isDialogVisible.value = false
    }

    fun onErrorRetry() {
        isDialogVisible.value = false
        doRequest()
    }

    private fun doRequest() {
        disposables += service.request()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = ::showErrorDialog
            )
    }

    private fun showErrorDialog(error: Throwable) {
        isDialogVisible.value = true
    }

    override fun onCleared() {
        disposables.dispose()
    }
}