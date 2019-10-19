package by.ve.dialogsbinding.ui.demo.dialog.solution2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.databinding.ActivitySolution1And2Binding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class Solution2Activity : AppCompatActivity() {

    private val errorView: ErrorView by inject { parametersOf(this) }

    private val viewModel by viewModel<Solution2ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySolution1And2Binding>(
            this,
            R.layout.activity_solution_1_and_2
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        errorView.viewModel = viewModel
    }
}