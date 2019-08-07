package com.mvvm.data.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mvvm.data.repo.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class, DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultsDao
}


