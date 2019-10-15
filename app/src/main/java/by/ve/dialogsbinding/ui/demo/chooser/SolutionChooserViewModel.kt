package by.ve.dialogsbinding.ui.demo.chooser

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.lifecycle.SingleLiveEvent


class SolutionChooserViewModel : ViewModel() {

    val solution1SelectedEvent = SingleLiveEvent<Void>()

    val solution2SelectedEvent = SingleLiveEvent<Void>()

    val solution3DialogSelectedEvent = SingleLiveEvent<Void>()

    val solution3EmbedSelectedEvent = SingleLiveEvent<Void>()

    fun onSolution1Click() {
        solution1SelectedEvent.call()
    }

    fun onSolution2Click() {
        solution2SelectedEvent.call()
    }

    fun onSolution3DialogClick() {
        solution3DialogSelectedEvent.call()
    }

    fun onSolution3EmbedClick() {
        solution3EmbedSelectedEvent.call()
    }
}