package by.ve.dialogsbinding.ui.toast

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.LayoutRes
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import by.ve.dialogsbinding.BR
import by.ve.dialogsbinding.R

object ToastDisplaySignal

private const val EMPTY_RESOURCE = -1
private const val DEFAULT_OFFSET = 0

@SuppressLint("Recycle")
class ToastShowingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var toast: Toast

    private lateinit var binding: ViewDataBinding

    var bindingData: ToastViewModel? = null
        set(value) {
            field = value
            value?.let { it ->
                binding.setVariable(BR.viewModel, it)
            }
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ToastShowingView).use {
            @LayoutRes
            val layoutResId = it.getResourceId(R.styleable.ToastShowingView_layout, EMPTY_RESOURCE)
            require(layoutResId != EMPTY_RESOURCE) {
                "Toast layout must be provided!"
            }

            val xOffset =
                it.getDimensionPixelSize(R.styleable.ToastShowingView_xOffset, DEFAULT_OFFSET)
            val yOffset =
                it.getDimensionPixelSize(R.styleable.ToastShowingView_yOffset, DEFAULT_OFFSET)

            val gravity = it.getInteger(R.styleable.ToastShowingView_gravity, Gravity.NO_GRAVITY)
            val duration = it.getInteger(R.styleable.ToastShowingView_duration, Toast.LENGTH_SHORT)

            createToast(context, layoutResId, gravity, xOffset, yOffset, duration)
        }
    }

    fun show() {
        toast.show()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    override fun onDetachedFromWindow() {
        toast.cancel()
        super.onDetachedFromWindow()
    }

    private fun createToast(
        context: Context,
        @LayoutRes layoutResId: Int,
        gravity: Int,
        xOffset: Int,
        yOffset: Int,
        durationFlag: Int
    ) {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            layoutResId,
            null,
            false
        )
        toast = Toast(context).apply {
            setGravity(gravity, xOffset, yOffset)
            duration = durationFlag
            view = binding.root
        }
    }
}

@BindingAdapter("toastDisplaySignal")
fun ToastShowingView.show(signal: ToastDisplaySignal?) {
    signal?.let { show() }
}

@BindingAdapter("toastViewModel")
fun ToastShowingView.bindViewModel(viewModel: ToastViewModel?) {
    if (viewModel != null) {
        bindingData = viewModel
    }
}