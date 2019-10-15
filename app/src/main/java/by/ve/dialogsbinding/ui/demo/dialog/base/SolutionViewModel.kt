package by.ve.dialogsbinding.ui.demo.dialog.base

import androidx.lifecycle.LiveData

const val STATE_VISIBLE = "Dialog is visible"

const val STATE_INVISIBLE = "Dialog is invisible"

interface SolutionViewModel {

    val dialogState: LiveData<String>

    fun doRequest()

    fun showErrorDialog()

    fun hideErrorDialog()

    fun onErrorCancel()

    fun onErrorRetry()
}