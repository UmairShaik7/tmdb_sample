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
package com.mvvm.tmdb

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.extentions.getYear
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.ui.adapter.GenreAdapter
import com.mvvm.tmdb.ui.adapter.MoviesAdapter


/**
 * [BindingAdapter]s for the [Movie]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Result>) {
    (listView.adapter as MoviesAdapter).submitList(items)
}

@BindingAdapter("app:setImage")
fun setImage(imageView: ImageView, path: String) {
    Glide.with(imageView.context)
            .load("${AppConstants.IMAGE_PATH}$path")
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)
}

@BindingAdapter("app:genre_items")
fun setGenreList(listView: RecyclerView, items: Array<AppConstants.Genre>) {
    (listView.adapter as GenreAdapter).submitList(items.asList())
}

@BindingAdapter("bindServerDate")
fun bindServerDate(textView: TextView, date: String) {
    if (date.isNotBlank()) textView.text = date.getYear()
}