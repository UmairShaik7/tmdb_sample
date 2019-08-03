package com.mvvm.data.repo.db

import android.content.Context
import androidx.room.Room

class DBService(val context: Context) {

    var database: AppDatabase? = null

    init {
        synchronized(this) {
            database = createDataBase(context)
        }
    }

    private fun createDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "Tasks.db"
        ).build()
    }
}