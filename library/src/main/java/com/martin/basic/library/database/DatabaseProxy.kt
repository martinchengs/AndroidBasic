package com.martin.basic.library.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Martin on 2017/8/23.
 * Hello World
 */
@Database(version = 1, entities = arrayOf(User::class))
abstract class DatabaseProxy : RoomDatabase() {

    abstract fun userDao(): UserDao
}