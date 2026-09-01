package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String = "",
    val email: String = "",
    val note: String = "",
    val passwordValue: String,
    val timestamp: Long = System.currentTimeMillis()
)
