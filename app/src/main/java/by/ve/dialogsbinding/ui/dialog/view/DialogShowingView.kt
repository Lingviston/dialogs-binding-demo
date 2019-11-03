package by.ve.dialogsbinding.ui.dialog.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
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
    @AttrRes defStyleAttr: Int = 0
) : View(context, attrs) {

    private lateinit var dialog: Dialog

    private lateinit var binding: ViewDataBinding

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
        context.obtainStyledAttributes(attrs, R.styleable.DialogShowingView, defStyleAttr, 0).use {
            @StyleRes
            val dialogStyle =
                it.getResourceId(R.styleable.DialogShowingView_dialogStyle, EMPTY_RESOURCE)
            require(dialogStyle != EMPTY_RESOURCE) {
                "Dialog style must be defined"
            }

            @LayoutRes
            val dialogLayout =
                it.getResourceId(R.styleable.DialogShowingView_dialogLayout, EMPTY_RESOURCE)
            require(dialogLayout != EMPTY_RESOURCE) {
                "Dialog layout must be defined"
            }

            createDialog(context, dialogLayout, dialogStyle)
        }
    }

    private fun createDialog(context: Context, @LayoutRes dialogLayout: Int, @StyleRes dialogStyle: Int) {
        val frameLayout = FrameLayout(context)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            dialogLayout,
            frameLayout,
            true
        )

        dialog = Dialog(context, dialogStyle).apply {
            setContentView(frameLayout)
            setCancelable(false)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    override fun setVisibility(visibility: Int) {
        dialogVisibility = visibility
    }

    override fun getVisibility() = dialogVisibility

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