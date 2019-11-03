package by.ve.dialogsbinding.ui.demo.toast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.databinding.FragmentToastsDemoBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToastsDemoFragment : Fragment() {

    private val navigationViewModel by sharedViewModel<ToastsDemoNavigationViewModel>()

    private val viewModel by viewModel<ToastsDemoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentToastsDemoBinding>(
        inflater,
        R.layout.fragment_toasts_demo,
        container,
        false
    ).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.navigationViewModel = navigationViewModel
        it.viewModel = viewModel
    }.root
}