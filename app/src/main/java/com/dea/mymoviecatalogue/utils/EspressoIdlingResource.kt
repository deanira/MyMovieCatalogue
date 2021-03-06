package com.dea.mymoviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }
    fun decrement() {
        idlingResource.decrement()
    }
    fun getEspressoIdlingResourceForMainActivity(): IdlingResource = idlingResource
}