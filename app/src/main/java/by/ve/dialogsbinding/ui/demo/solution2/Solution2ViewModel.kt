package by.ve.dialogsbinding.ui.demo.solution2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.service.FirstTryFailingService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class Solution2ViewModel(
    private val service: FirstTryFailingService
) : ViewModel() {

    val isDialogVisible = MutableLiveData<Boolean>()

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
