package com.mvvm.data.repo.db

import androidx.room.TypeConverter
import com.mvvm.data.repo.extentions.toDateLong
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}



