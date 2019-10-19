package by.ve.dialogsbinding.ui.demo.dialog.solution3

import androidx.lifecycle.MutableLiveData
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.dialog.base.BaseSolutionViewModel
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig

class Solution3ViewModel(
    service: FirstTryFailingService
) : BaseSolutionViewModel(service) {

    val isDialogVisible = MutableLiveData<Boolean>(false)

    val errorDialogConfig = DialogUiConfig(
        title = R.string.error_title,
        message = R.string.error_message,
        positiveButtonText = R.string.error_retry,
        negativeButtonText = R.string.error_cancel
    )

    val errorDialogViewModel = DialogViewModel(
        positiveClick = ::onErrorRetry,
        negativeClick = ::onErrorCancel
    )

    override fun showErrorDialog() {
        isDialogVisible.value = true
    }

    override fun hideErrorDialog() {
        isDialogVisible.value = false
    }
}