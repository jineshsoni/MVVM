package com.thinkwik.boilerplate.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

// Use object so we have a singleton instance
object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class tableType
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}

class RxEvent {
    data class EventString(val str: String)
    data class EventErrorData(val code: Int = 999, val error: String)
}