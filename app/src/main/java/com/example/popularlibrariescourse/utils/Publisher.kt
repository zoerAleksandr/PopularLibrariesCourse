package com.example.popularlibrariescourse.utils

class Publisher<T>(private val singleTask: Boolean = false) {
    private val listSubscriber: MutableSet<Subscriber<T>> = mutableSetOf()
    private var value: T? = null
    private var hasFirstValue = false

    fun subscribe(subscriber: Subscriber<T>) {
        listSubscriber.add(subscriber)
        if (hasFirstValue) {
            value?.let { subscriber.invoke(it) }
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
        listSubscriber.forEach { it.invoke(value) }
    }
}

private typealias Subscriber<T> = (T?) -> Unit