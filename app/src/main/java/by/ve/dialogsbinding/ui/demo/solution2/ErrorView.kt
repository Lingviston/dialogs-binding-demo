package by.ve.dialogsbinding.ui.demo.solution2

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import by.ve.dialogsbinding.lifecycle.observe
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEvent
import by.ve.dialogsbinding.ui.dialog.fragment.DialogEventBus
import by.ve.dialogsbinding.ui.dialog.fragment.DialogNavigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

private const val DIALOG_TAG = "Error"

class ErrorView(
    private val lifecycleOwner: LifecycleOwner,
    private val dialogNavigator: DialogNavigator,
    private val dialogEventBus: DialogEventBus
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
    private val disposables = CompositeDisposable()

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        disposables += dialogEventBus.getEvents(DIALOG_TAG)
            .subscribe {
                when (it) {
                    is DialogEvent.PositiveButtonClickEvent ->
                        viewModel?.onErrorRetry()
                    is DialogEvent.NegativeButtonClickEvent ->
                        viewModel?.onErrorCancel()
                }
            }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        disposables.dispose()
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