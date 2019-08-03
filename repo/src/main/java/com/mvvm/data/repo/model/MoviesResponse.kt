package com.mvvm.data.repo.model

import android.content.Context
import androidx.room.*


data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

@Entity
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

@Dao
interface ResultsDao {
    @Query("SELECT * FROM result")
    fun getAll(): List<Result>
}

@Database(entities = arrayOf(Result::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultsDao
}

private fun createDataBase(context: Context): AppDatabase {
    val result = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "Tasks.db"
    ).build()
    return result
}