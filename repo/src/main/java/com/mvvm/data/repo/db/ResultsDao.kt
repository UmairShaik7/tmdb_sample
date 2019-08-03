package com.mvvm.data.repo.db

import androidx.room.Dao
import androidx.room.Query
import com.mvvm.data.repo.model.Result

@Dao
interface ResultsDao {
    @Query("SELECT * FROM result")
    fun getAll(): List<Result>
}