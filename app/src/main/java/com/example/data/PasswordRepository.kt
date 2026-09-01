package com.example.data

import kotlinx.coroutines.flow.Flow

class PasswordRepository(private val passwordDao: PasswordDao) {
    val allPasswords: Flow<List<PasswordEntry>> = passwordDao.getAllPasswords()

    suspend fun getPasswordById(id: Int): PasswordEntry? = passwordDao.getPasswordById(id)

    suspend fun insert(password: PasswordEntry) = passwordDao.insertPassword(password)

    suspend fun deleteById(id: Int) = passwordDao.deletePasswordById(id)
}
