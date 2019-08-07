package com.mvvm.data.repo.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {

    @TypeConverter
    fun fromGenre_ids(countryLang: List<Int>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {

        }.getType()
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toGenre_ids(countryLangString: String?): List<Int>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {

        }.getType()
        return gson.fromJson(countryLangString, type)
    }

}