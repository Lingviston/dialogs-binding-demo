package by.ve.dialogsbinding.ui.dialog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import by.ve.dialogsbinding.BR
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.lifecycle.observeEmptyEvent
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val EXTRA_UI_CONFIG = "EXTRA_UI_CONFIG"

class CommonDialogFragment : DialogFragment() {

    companion object {

        fun newInstance(uiConfig: DialogUiConfig) = CommonDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_UI_CONFIG, uiConfig)
            }
        }
    }

    private val dialogEventBus by inject<DialogEventBus>()

    private val viewModel by viewModel<DialogViewModel>()

    private val uiConfig by lazy { arguments!!.getParcelable<DialogUiConfig>(EXTRA_UI_CONFIG) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        observeEmptyEvent(viewModel.positiveButtonClickEvent) {
            dialogEventBus.sendEvent(tag!!, DialogEvent.PositiveButtonClickEvent)
        }
        observeEmptyEvent(viewModel.negativeButtonClickEvent) {
            dialogEventBus.sendEvent(tag!!, DialogEvent.NegativeButtonClickEvent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FrameLayout(requireActivity()).also {
        DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.dialog_error, it, true)
            ?.apply {
                lifecycleOwner = viewLifecycleOwner
                setVariable(BR.viewModel, viewModel)
                setVariable(BR.uiConfig, uiConfig)
            }
    }
}