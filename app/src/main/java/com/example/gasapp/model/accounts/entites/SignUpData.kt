package com.example.gasapp.model.accounts.entites

import com.example.gasapp.utils.EmptyFieldException
import com.example.gasapp.utils.Field
import com.example.gasapp.utils.PasswordMismatchException

data class SignUpData(
    val username: String,
    val email: String,
    val password: String,
    val repeatPassword: String
) {
    fun validate() {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (username.isBlank()) throw EmptyFieldException(Field.Username)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        if (password != repeatPassword) throw PasswordMismatchException()
    }
}