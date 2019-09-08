package by.ve.dialogsbinding.solutions.chooser

import androidx.lifecycle.ViewModel
import by.ve.dialogsbinding.lifecycle.SingleLiveEvent


class SolutionChooserViewModel : ViewModel() {

    val solution1SelectedEvent = SingleLiveEvent<Void>()

    val solution2SelectedEvent = SingleLiveEvent<Void>()

    val solution3SelectedEvent = SingleLiveEvent<Void>()

    fun onSolution1Click() {
        solution1SelectedEvent.call()
    }

    fun onSolution2Click() {
        solution2SelectedEvent.call()
    }

    fun onSolution3Click() {
        solution3SelectedEvent.call()
    }
}