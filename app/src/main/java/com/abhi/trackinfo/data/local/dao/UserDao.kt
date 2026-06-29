package com.abhi.trackinfo.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abhi.trackinfo.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Query(
        """
        SELECT * FROM users
        WHERE email = :email AND password =:password
    """
    )
    suspend fun login(
        email: String,
        password: String
    ): UserEntity

    @Insert
    suspend fun register(
        userEntity: UserEntity
    ): Long?

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): UserEntity

}
