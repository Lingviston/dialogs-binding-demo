package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.lifecycle.LiveData

const val STATE_NOT_DONE = "Request NOT DONE"

const val STATE_SUCCESS = "Request SUCCESSFUL"

const val STATE_ERROR = "Request FAILED"

interface SolutionViewModel {

    val requestState: LiveData<String>

    fun doRequest()

    fun showErrorDialog()

    fun hideErrorDialog()

    fun onErrorCancel()

    fun onErrorRetry()
}