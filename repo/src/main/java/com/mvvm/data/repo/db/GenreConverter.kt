package com.mvvm.data.repo.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {
    val gson = Gson()
    @TypeConverter
    fun fromGenreIds(countryLang: List<Int>?): String? {
        if (countryLang == null) {
            return null
        }

        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toGenreIds(countryLangString: String?): List<Int>? {
        if (countryLangString == null) {
            return null
        }

        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(countryLangString, type)
    }

}