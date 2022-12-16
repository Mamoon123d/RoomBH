package com.e.roomexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.roomexample.data.model

@Database(entities = [model::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {
    abstract fun myDao(): MyDao
    companion object {
        private var INSTANCE: Db? = null
        fun getDatabase(context: Context): Db {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            Db::class.java,
                            "bh_database")
                            .build()
                }
            }

            return INSTANCE!!
        }
    }
}