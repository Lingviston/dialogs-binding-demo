package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.lifecycle.LiveData

interface SolutionViewModel {

    val requestState: LiveData<Int>

    fun doRequest()

    fun showErrorDialog()

    fun hideErrorDialog()

    fun onErrorCancel()

    fun onErrorRetry()
}