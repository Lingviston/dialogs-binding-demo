package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.annotation.CallSuper
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

    private val _dialogState = MutableLiveData<String>(STATE_INVISIBLE)

    override val dialogState: LiveData<String> get() = _dialogState

    private val disposables = CompositeDisposable()

    override fun onErrorCancel() {
        hideErrorDialog()
    }

    override fun onErrorRetry() {
        hideErrorDialog()
        doRequest()
    }

    @CallSuper
    override fun showErrorDialog() {
        _dialogState.value = STATE_VISIBLE
    }

    @CallSuper
    override fun hideErrorDialog() {
        _dialogState.value = STATE_INVISIBLE
    }

    override fun doRequest() {
        disposables += service.request()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { showErrorDialog() }
            )
    }

    override fun onCleared() {
        disposables.dispose()
    }
}