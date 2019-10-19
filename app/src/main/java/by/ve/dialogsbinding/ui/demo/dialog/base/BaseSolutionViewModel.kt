package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.service.FirstTryFailingService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

abstract class BaseSolutionViewModel(
    private val service: FirstTryFailingService
) : ViewModel(), SolutionViewModel {

    private val _requestState = MutableLiveData<Int>(R.string.request_state_not_done)

    override val requestState: LiveData<Int> get() = _requestState

    private val disposables = CompositeDisposable()

    override fun onErrorCancel() {
        hideErrorDialog()
    }

    override fun onErrorRetry() {
        hideErrorDialog()
        doRequest()
    }

    override fun doRequest() {
        disposables += service.request()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    _requestState.value = R.string.request_state_success
                    hideErrorDialog()
                },
                onError = {
                    _requestState.value = R.string.request_state_failed
                    showErrorDialog()
                }
            )
    }

    override fun onCleared() {
        disposables.dispose()
    }
}