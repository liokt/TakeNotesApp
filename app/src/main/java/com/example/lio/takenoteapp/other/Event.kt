package com.example.lio.takenoteapp.other

open class Event<out T>(val content: T) {

    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled() = if(hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    fun peekContent() = content
}