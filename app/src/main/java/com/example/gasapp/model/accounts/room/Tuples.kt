package com.example.gasapp.model.accounts.room

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class AccountSignInTuple(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "password") val password: String
)

data class AccountUpdateUsernameTuple(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "username") val username: String
)