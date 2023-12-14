package com.example.gasapp.model.accounts.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.gasapp.model.accounts.entites.Account
import com.example.gasapp.model.accounts.entites.SignUpData

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) val email: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
) {

    fun toAccount(): Account = Account(
        id = id,
        email = email,
        username = username,
        createdAt = createdAt
    )

    companion object {
        fun fromSignUpData(signUpData: SignUpData) = AccountDbEntity(
            id = 0,
            email = signUpData.email,
            username = signUpData.username,
            password = signUpData.password,
            createdAt = System.currentTimeMillis()
        )
    }
}