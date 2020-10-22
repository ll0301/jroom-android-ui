package com.example.jroomui.util

object EspressoIdlingResource {
    
    private val RESOURCE = "GLOBAL"
    
    @JvmField val countingIdlingResource = SimpleCountingIdlingResource(RESOURCE)
    
    fun increment() {
        countingIdlingResource.increment()
    }
    
    fun decrement() {
        countingIdlingResource.decrement()
    }
}