package com.example.popularlibrariescourse.utils

class Publisher<T> {
    private val listSubscriber: MutableSet<Subscriber<T>> = mutableSetOf()
    private var value: T? = null

    fun subscribe(subscriber: Subscriber<T>) {
        listSubscriber.add(subscriber)
        value?.let { subscriber.invoke(it) }
    }

    fun unsubscribe() {
        listSubscriber.clear()
    }

    fun post(value: T) {
        listSubscriber.forEach { it.invoke(value) }
        this.value = value
    }
}

private typealias Subscriber<T> = (T?) -> Unit