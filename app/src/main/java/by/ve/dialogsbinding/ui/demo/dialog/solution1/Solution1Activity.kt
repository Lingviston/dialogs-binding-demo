package by.ve.dialogsbinding.ui.demo.dialog.solution1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.databinding.ActivitySolution1And2Binding
import by.ve.dialogsbinding.lifecycle.observe
import by.ve.dialogsbinding.ui.demo.dialog.solution1.DialogControlEvent.Hide
import by.ve.dialogsbinding.ui.demo.dialog.solution1.DialogControlEvent.Show
import by.ve.dialogsbinding.ui.dialog.fragment.DialogNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class Solution1Activity : AppCompatActivity() {

    private val dialogNavigator: DialogNavigator by inject { parametersOf(this) }

    private val viewModel by viewModel<Solution1ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySolution1And2Binding>(
            this,
            R.layout.activity_solution_1_and_2
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        observe(viewModel.dialogControlEvent, ::showOrHideDialog)
    }

    private fun showOrHideDialog(event: DialogControlEvent) {
        when (event) {
            is Show -> dialogNavigator.showDialog(event.tag, event.uiConfig)
            is Hide -> dialogNavigator.hideDialog(event.tag)
        }
    }
}