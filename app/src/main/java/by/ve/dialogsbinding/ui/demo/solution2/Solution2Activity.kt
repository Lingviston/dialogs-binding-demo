package by.ve.dialogsbinding.ui.demo.solution2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class Solution2Activity : AppCompatActivity() {

    private val errorView: ErrorView by inject { parametersOf(this) }

    private val viewModel by viewModel<Solution2ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorView.viewModel = viewModel
    }
}