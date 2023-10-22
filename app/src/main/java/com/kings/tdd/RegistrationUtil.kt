package com.kings.tdd

object RegistrationUtil {

    val existingUsernames = listOf("Carl", "Peter")

    fun validateRegistrationInputs(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean{

        if(username.isEmpty() || password.isEmpty()){
            return false
        }

        if(username in existingUsernames){
            return false
        }

        if(password != confirmPassword){
            return false
        }

        if(password.count { it.isDigit() } < 2){
            return false
        }

        return true
    }
}