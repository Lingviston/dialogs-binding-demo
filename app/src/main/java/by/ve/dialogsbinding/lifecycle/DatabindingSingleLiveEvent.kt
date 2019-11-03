package com.vmn.playplex.arch

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
class DatabindingSingleLiveEvent<T> : MutableLiveData<T>() {

    private val pendingNotification = AtomicBoolean(false)
    private val pendingValue = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer { t -> notifyObserverIfPending(t, observer) })
    }

    override fun observeForever(observer: Observer<in T>) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observeForever { t -> notifyObserverIfPending(t, observer) }
    }

    @MainThread
    override fun setValue(t: T?) {
        pendingNotification.set(true)
        pendingValue.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    private fun notifyObserverIfPending(t: T?, observer: Observer<in T>) {
        if (pendingNotification.compareAndSet(true, false)) {
            observer.onChanged(t)
        }
    }

    override fun getValue(): T? =
        if (pendingValue.compareAndSet(true, false)) {
            super.getValue()
        } else {
            null
        }

    companion object {

        private const val TAG = "BindingSingleLiveEvent"
    }
}