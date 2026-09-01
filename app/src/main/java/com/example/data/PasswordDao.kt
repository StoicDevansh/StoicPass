package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Query("SELECT * FROM passwords ORDER BY timestamp DESC")
    fun getAllPasswords(): Flow<List<PasswordEntry>>

    @Query("SELECT * FROM passwords WHERE id = :id LIMIT 1")
    suspend fun getPasswordById(id: Int): PasswordEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(password: PasswordEntry)

    @Query("DELETE FROM passwords WHERE id = :id")
    suspend fun deletePasswordById(id: Int)
}
