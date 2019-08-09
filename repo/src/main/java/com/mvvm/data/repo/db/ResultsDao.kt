package com.mvvm.data.repo.db

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


    /*@Query("SELECT * FROM result ORDER BY release_date")
    fun getLatestMovies(): List<Result>

    @Query("SELECT * FROM result WHERE release_date BETWEEN :from AND :to")
    fun getLatestMovies(from: Date, to: Date): List<Result>*/

    @Query("SELECT * FROM result WHERE category like :category")
    fun getMovies(category: Int): List<Result>

    @Query("SELECT * FROM result Where genre_ids like :genreType")
    fun getMoviesWithGenre(genreType: Int): List<Result>
}