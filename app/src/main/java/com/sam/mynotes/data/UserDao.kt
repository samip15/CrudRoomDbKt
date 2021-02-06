package com.sam.mynotes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sam.mynotes.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT *FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}