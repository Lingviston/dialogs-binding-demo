package by.ve.dialogsbinding.lifecycle

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders


inline fun <reified ViewModelT : ViewModel> FragmentActivity.loadViewModel() = lazy {
    ViewModelProviders.of(this).get(ViewModelT::class.java)
}

inline fun LifecycleOwner.observe(
    singleLiveEvent: SingleLiveEvent<Void>,
    crossinline observer: () -> Unit
) {
    singleLiveEvent.observe(this, Observer {
        observer.invoke()
    })
}