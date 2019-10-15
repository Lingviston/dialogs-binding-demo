package by.ve.dialogsbinding.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    crossinline observer: (T) -> Unit
) {
    liveData.observe(this, Observer {
        observer.invoke(it)
    })
}

inline fun LifecycleOwner.observeEmptyEvent(
    liveData: LiveData<Void>,
    crossinline observer: () -> Unit
) {
    liveData.observe(this, Observer {
        observer.invoke()
    })
}