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

import androidx.lifecycle.LiveData
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.extentions.mapInPlace
import com.mvvm.data.repo.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Concrete implementation of a data source as a db.
 */
class LocalDataSource(val db: DBService) {

    suspend fun getLatestMovies(): LiveData<List<Result>> = withContext(Dispatchers.IO) {
        //db.database.resultDao().getLatestMovies(getThisYearStart(), getThisYearEnd())
        db.database.resultDao().getMovies(AppConstants.MovieCategories.LATEST_MOVIES.type)
    }


    suspend fun insertTopMovies(results: MutableList<Result>) = withContext(Dispatchers.IO) {
        results.let { list ->
            list.mapInPlace { it.copy(category = AppConstants.MovieCategories.TOP_MOVIES.type) }
            //list.updateCategory(AppConstants.MovieCategories.TOP_MOVIES.type)
            db.database.resultDao().saveMovies(results)
        }
    }

    suspend fun getTopMovies(): LiveData<List<Result>> = withContext(Dispatchers.Main) {
        db.database.resultDao().getMovies(AppConstants.MovieCategories.TOP_MOVIES.type)
    }

    suspend fun insertLatestMovies(results: MutableList<Result>) = withContext(Dispatchers.IO) {
        results.let {
            it.mapInPlace { it.copy(category = AppConstants.MovieCategories.LATEST_MOVIES.type) }
            db.database.resultDao().saveMovies(results)
        }
    }

}


