package com.mvvm.data.repo.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.data.repo.model.Result


@Dao
interface ResultsDao {
    @Query("SELECT * FROM result")
    fun getAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(results: List<Result>?)

    @Query("SELECT * FROM result WHERE category like :category")
    fun getMovies(category: Int): List<Result>


    @Query("SELECT * FROM result WHERE category like :category")
    fun getMoviesLiveData(category: Int): List<Result>

    @Query("SELECT * FROM result Where genre_ids like :genreType")
    fun getMoviesWithGenre(genreType: Int): List<Result>

    @Query("SELECT * FROM result")
    fun getAllMovies(): LiveData<List<Result>>

    @Query("SELECT * FROM result where genre_ids like :item ORDER BY original_title ASC")
    fun getGenra(item: String): DataSource.Factory<Int, Result>

}