package com.kings.tdd

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.validateRegistrationInputs(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInputs(
            "Ada",
            "",
            ""
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `not matching confirm password returns false`(){
        val result = RegistrationUtil.validateRegistrationInputs(
            "Ada",
            "1234",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password less than two digits returns false`(){
        val result = RegistrationUtil.validateRegistrationInputs(
            "Men",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `non empty username and matching password returns true`(){
        val result = RegistrationUtil.validateRegistrationInputs(
            "Ada",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exist`(){
        val result = RegistrationUtil.validateRegistrationInputs(
            "Carl",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
}