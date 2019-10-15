package by.ve.dialogsbinding.ui.dialog.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import by.ve.dialogsbinding.BR
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.ui.dialog.common.DialogUiConfig
import by.ve.dialogsbinding.ui.dialog.common.IDialogViewModel

private const val EMPTY_RESOURCE = -1

@SuppressLint("Recycle")
class DialogShowingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs) {

    private var dialog: Dialog

    private var binding: ViewDataBinding

    private var dialogVisibility: Int = GONE
        set(value) {
            field = value
            if (value == VISIBLE) {
                dialog.show()
            } else {
                dialog.dismiss()
            }
        }

    var bindingData: Pair<DialogUiConfig?, IDialogViewModel?>? = null
        set(value) {
            field = value
            value?.let { (config, viewModel) ->
                binding.setVariable(BR.uiConfig, config)
                binding.setVariable(BR.viewModel, viewModel)
            }
        }

    init {
        var dialogStyle = EMPTY_RESOURCE
        var dialogLayout = EMPTY_RESOURCE
        context.obtainStyledAttributes(attrs, R.styleable.DialogShowingView, defStyleAttr, 0).use {
            @StyleRes
            dialogStyle =
                it.getResourceId(R.styleable.DialogShowingView_dialogStyle, EMPTY_RESOURCE)
            require(dialogStyle != EMPTY_RESOURCE) {
                "Dialog style must be defined"
            }

            @LayoutRes
            dialogLayout =
                it.getResourceId(R.styleable.DialogShowingView_dialogLayout, EMPTY_RESOURCE)
            require(dialogLayout != EMPTY_RESOURCE) {
                "Dialog layout must be defined"
            }
        }
        val frameLayout = FrameLayout(context)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            dialogLayout,
            frameLayout,
            true
        )

        dialog = Dialog(context, dialogStyle).apply {
            setContentView(frameLayout)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    override fun setVisibility(visibility: Int) {
        dialogVisibility = visibility
    }

    override fun getVisibility(): Int {
        return dialogVisibility
    }

    /**
     * Sometimes while showing the dialog we need to replace its holder fragment or activity. In this case we
     * need to dismiss dialog.
     */
    override fun onDetachedFromWindow() {
        dialog.dismiss()
        super.onDetachedFromWindow()
    }
}

@BindingAdapter(value = ["dialogConfig", "dialogViewModel"], requireAll = false)
fun DialogShowingView.bindTextAndActions(
    dialogConfig: DialogUiConfig? = null,
    dialogViewModel: IDialogViewModel? = null
) {
    bindingData = Pair(dialogConfig, dialogViewModel)
}