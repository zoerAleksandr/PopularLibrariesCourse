package com.example.popularlibrariescourse.utils

class Publisher<T> {
    private var listSubscriber: MutableSet<Subscriber<T>> = mutableSetOf()

    fun subscribe(subscriber: Subscriber<T>) {
        listSubscriber.add(subscriber)
    }

    fun unsubscribe(subscriber: Subscriber<T>?){
        subscriber?.let {
            listSubscriber.remove(it)
        }
    }

    fun post(value: Any) {
        listSubscriber.forEach { it.post(value) }
    }
}