package by.ve.dialogsbinding.ui.demo.dialog.solution2

import androidx.lifecycle.MutableLiveData
import by.ve.dialogsbinding.service.FirstTryFailingService
import by.ve.dialogsbinding.ui.demo.dialog.base.BaseSolutionViewModel

class Solution2ViewModel(
    service: FirstTryFailingService
) : BaseSolutionViewModel(service) {

    val isDialogVisible = MutableLiveData<Boolean>(false)

    override fun showErrorDialog() {
        super.showErrorDialog()
        isDialogVisible.value = true
    }

    override fun hideErrorDialog() {
        super.hideErrorDialog()
        isDialogVisible.value = false
    }
}
