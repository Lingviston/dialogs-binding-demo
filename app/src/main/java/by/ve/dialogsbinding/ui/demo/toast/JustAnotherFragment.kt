package by.ve.dialogsbinding.ui.demo.toast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import by.ve.dialogsbinding.R
import by.ve.dialogsbinding.databinding.FragmentJustAnotherBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class JustAnotherFragment : Fragment() {

    private val viewModel by sharedViewModel<ToastsDemoNavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentJustAnotherBinding>(
        inflater,
        R.layout.fragment_just_another,
        container,
        false
    ).also {
        it.lifecycleOwner = viewLifecycleOwner
        it.viewModel = viewModel
    }.root
}