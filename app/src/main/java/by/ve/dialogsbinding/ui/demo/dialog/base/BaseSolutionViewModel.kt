package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.service.FirstTryFailingService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy


abstract class BaseSolutionViewModel(
    private val service: FirstTryFailingService
) : ViewModel(), SolutionViewModel {

    protected val disposables = CompositeDisposable()

    init {
        doRequest()
    }

    override fun onErrorCancel() {
        hideErrorDialog()
    }

    override fun onErrorRetry() {
        hideErrorDialog()
        doRequest()
    }

    private fun doRequest() {
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