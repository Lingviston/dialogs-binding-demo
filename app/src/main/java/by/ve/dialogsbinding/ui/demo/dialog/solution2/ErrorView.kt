package by.ve.dialogsbinding.ui.demo.dialog.solution2

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import by.ve.dialogsbinding.lifecycle.observe
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent.NegativeButtonClickEvent
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent.PositiveButtonClickEvent
import by.ve.dialogsbinding.ui.dialog.fragment.DialogNavigator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

private const val DIALOG_TAG = "Error"

class ErrorView(
    private val lifecycleOwner: LifecycleOwner,
    private val dialogNavigator: DialogNavigator,
    private val dialogEventBus: EventBus
) : DefaultLifecycleObserver {

    var viewModel: Solution2ViewModel? = null
        set(value) {
            field = value
            value?.let {
                bind(it)
            }
        }

    private val errorDialogUiConfig by lazy {
        DialogUiConfig(
            title = "Error",
            message = "Request failed",
            positiveButtonText = "Retry",
            negativeButtonText = "Cancel"
        )
    }

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        dialogEventBus.register(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        dialogEventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPositiveButtonClick(event: PositiveButtonClickEvent) {
        event.doIfTagMatches(DIALOG_TAG) { viewModel?.onErrorRetry() }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNegativeButtonClick(event: NegativeButtonClickEvent) {
        event.doIfTagMatches(DIALOG_TAG) { viewModel?.onErrorCancel() }
    }

    private fun bind(viewModel: Solution2ViewModel) {
        with(lifecycleOwner) {
            observe(viewModel.isDialogVisible, ::updateErrorDialogState)
        }
    }

    private fun updateErrorDialogState(visible: Boolean) {
        if (visible) {
            dialogNavigator.showDialog(DIALOG_TAG, errorDialogUiConfig)
        } else {
            dialogNavigator.hideDialog(DIALOG_TAG)
        }
    }
}