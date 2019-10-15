package by.ve.dialogsbinding.ui.demo.solution3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.databinding.ActivitySolution3DialogBinding
import by.ve.dialogsbinding.databinding.ActivitySolution3EmbedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

enum class ErrorStyle { DIALOG, EMBED }

class Solution3Activity : AppCompatActivity() {

    private val viewModel by viewModel<Solution3ViewModel>()

    private val errorStyle: ErrorStyle by lazy { intent.getSerializableExtra(EXTRA_ERROR_STYLE) as ErrorStyle}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView()
    }

    private fun bindContentView() {
        when(errorStyle) {
            ErrorStyle.DIALOG -> DataBindingUtil.setContentView<ActivitySolution3DialogBinding>(this, R.layout.activity_solution3_dialog)
                .also {
                    it.viewModel = viewModel
                }
            ErrorStyle.EMBED -> DataBindingUtil.setContentView<ActivitySolution3EmbedBinding>(this, R.layout.activity_solution3_embed)
                .also {
                    it.viewModel = viewModel
                }
        }.lifecycleOwner = this
    }

    companion object {

        private const val EXTRA_ERROR_STYLE = "EXTRA_ERROR_STYLE"

        fun start(context: Context, errorStyle: ErrorStyle) {
            val intent = Intent(context, Solution3Activity::class.java)
                .putExtra(EXTRA_ERROR_STYLE, errorStyle)
            context.startActivity(intent)
        }
    }
}