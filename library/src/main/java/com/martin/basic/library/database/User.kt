package com.martin.basic.library.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Martin on 2017/8/23.
 * Hello World
 */
@Entity
data class User(
        @PrimaryKey @ColumnInfo(name = "user_id") var id: Long
        , @ColumnInfo(name = "user_name") var userName: String
        , @ColumnInfo(name = "user_age") var grander: Boolean)