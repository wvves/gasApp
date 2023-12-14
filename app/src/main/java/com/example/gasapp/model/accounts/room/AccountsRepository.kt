package com.example.gasapp.model.accounts.room

import com.example.gasapp.model.accounts.entites.Account
import com.example.gasapp.model.accounts.entites.SignUpData
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    suspend fun isSignedIn(): Boolean

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(signUpData: SignUpData)

    suspend fun logout()

    suspend fun getAccount(): Flow<Account?>

    suspend fun updateAccountUsername(newUsername: String)
}