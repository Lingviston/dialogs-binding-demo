package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.service.FirstTryFailingService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy


abstract class BaseSolutionViewModel(
    private val service: FirstTryFailingService
) : ViewModel(), SolutionViewModel {

    private val _requestState = MutableLiveData<String>(STATE_NOT_DONE)

    override val requestState: LiveData<String> get() = _requestState

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
                    _requestState.value = STATE_SUCCESS
                },
                onError = {
                    _requestState.value = STATE_ERROR
                    showErrorDialog()
                }
            )
    }

    override fun onCleared() {
        disposables.dispose()
    }
}