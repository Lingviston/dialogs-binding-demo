package by.ve.dialogsbinding.ui.dialog.fragment

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class DialogEventBus {

    private val subjects = mutableMapOf<String, PublishSubject<DialogEvent>>()

    fun sendEvent(tag: String, event: DialogEvent) {
        getOrCreateEventStream(tag).onNext(event)
    }

    fun getEvents(tag: String): Observable<DialogEvent> = getOrCreateEventStream(tag)

    private fun getOrCreateEventStream(tag: String) = subjects.getOrPut(tag) {
        PublishSubject.create<DialogEvent>()
    }
}