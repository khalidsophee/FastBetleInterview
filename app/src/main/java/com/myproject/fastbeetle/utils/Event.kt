package com.myproject.fastbeetle.utils

import androidx.lifecycle.Observer
import com.myproject.fastbeetle.data.Items

/**
 * Created by Khalid Aziz
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
abstract open class Event<out T>(private val content: T) : List<Items> {

    var hasBeenHandled = false
        private set

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}