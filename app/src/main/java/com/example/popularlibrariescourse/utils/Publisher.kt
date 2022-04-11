package com.example.popularlibrariescourse.utils

import android.os.Handler

class Publisher<T>(private val singleTask: Boolean = false) {
    private val listSubscriber: MutableSet<Subscriber<T>> = mutableSetOf()
    private var value: T? = null
    private var hasFirstValue = false

    fun subscribe(handler: Handler, callback: (T?) -> Unit) {
        val subscriber = Subscriber<T>(handler, callback)
        listSubscriber.add(subscriber)
        if (hasFirstValue) {
            subscriber.post(value)
        }
    }

    fun unsubscribe() {
        listSubscriber.clear()
    }

    fun post(value: T) {
        if (!singleTask) {
            hasFirstValue = true
            this.value = value
        }
        listSubscriber.forEach { it.post(value) }
    }
}

private data class Subscriber<T>(val handler: Handler, val callback: (T?) -> Unit) {
    fun post(value: T?) {
        handler.post {
            callback.invoke(value)
        }
    }
}