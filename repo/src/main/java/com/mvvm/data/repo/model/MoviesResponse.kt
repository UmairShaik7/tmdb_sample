package com.mvvm.data.repo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: MutableList<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

@Entity
data class Result(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
    var category: Int
)
