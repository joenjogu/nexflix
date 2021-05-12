@file:Suppress("UNCHECKED_CAST")

package com.joenjogu.nexflix.utilities

import androidx.lifecycle.LiveData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> getValue(liveData: LiveData<T>): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    liveData.observeForever {
        data[0] = it
        latch.countDown()
    }
    latch.await(2, TimeUnit.SECONDS)

    return data[0] as T
}
