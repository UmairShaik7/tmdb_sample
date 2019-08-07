/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mvvm.data.repo.db

import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.extentions.mapInPlace
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.result.DBResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


/**
 * Concrete implementation of a data source as a db.
 */
class LocalDataSource(val db: DBService) {

    suspend fun getLatestMovies(): DBResult<List<Result>> = withContext(Dispatchers.IO) {
        return@withContext try {
            DBResult.Success(
                //db.database.resultDao().getLatestMovies(getThisYearStart(), getThisYearEnd())
                db.database.resultDao().getMovies(AppConstants.MovieCategories.LATEST_MOVIES.type)
            )
        } catch (e: Exception) {
            Error(e)
        }
    } as DBResult<List<Result>>

    private fun getThisYearStart(): Date {

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.DAY_OF_YEAR, 1)
        val start = cal.time
        return start


    }

    private fun getThisYearEnd(): Date {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, 11) // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31) // new years eve

        val end = cal.getTime()

        return end
    }


    suspend fun insertTopMovies(results: MutableList<Result>?) = withContext(Dispatchers.IO) {
        results?.let {
            it.mapInPlace { it.copy(category = AppConstants.MovieCategories.TOP_MOVIES.type) }
            //it.updateCategory(AppConstants.MovieCategories.TOP_MOVIES.type)
            db.database.resultDao().saveMovies(results)
        }
    }

    suspend fun getTopMovies(): DBResult<List<Result>> = withContext(Dispatchers.IO) {
        return@withContext try {
            DBResult.Success(db.database.resultDao().getMovies(AppConstants.MovieCategories.TOP_MOVIES.type))
        } catch (e: Exception) {
            Error(e)
        }
    } as DBResult<List<Result>>

    suspend fun insertLatestMovies(results: MutableList<Result>) = withContext(Dispatchers.IO){
        results?.let {
            it.mapInPlace { it.copy(category = AppConstants.MovieCategories.LATEST_MOVIES.type) }
            db.database.resultDao().saveMovies(results)
        }
    }

}


