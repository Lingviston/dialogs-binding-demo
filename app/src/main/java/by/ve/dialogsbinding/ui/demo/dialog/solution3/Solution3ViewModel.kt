package by.ve.dialogsbinding.ui.demo.dialog.solution3

import androidx.lifecycle.MutableLiveData
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.dialog.base.BaseSolutionViewModel
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig


class Solution3ViewModel(
    service: FirstTryFailingService
) : BaseSolutionViewModel(service) {

    val isDialogVisible = MutableLiveData<Boolean>(false)

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

    override fun showErrorDialog() {
        isDialogVisible.value = true
    }

    override fun hideErrorDialog() {
        isDialogVisible.value = false
    }
}