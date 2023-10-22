package com.kings.tdd

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ResourceComparerTest {

    private lateinit var resourceComparer: ResourceComparer
    private lateinit var context: Context

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun givenResourceIdSameAsString_returnTrue(){
        val result = resourceComparer.compareString(context, R.string.app_name, "TDD")
        assertThat(result).isTrue()
    }

    @Test
    fun givenResourceIdDifferentAsString_returnFalse(){
        val result = resourceComparer.compareString(context, R.string.app_name, "Hello")
        assertThat(result).isFalse()
    }
}