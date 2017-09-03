package com.martin.basic.library.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

/**
 * Created by Martin on 2017/8/23.
 * Hello World
 */
@Dao
interface UserDao {
    @Query("SELECT * from User")
    fun getAll(): List<User>
}