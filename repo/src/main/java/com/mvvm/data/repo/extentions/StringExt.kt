package com.mvvm.data.repo.extentions

import android.os.Build
import com.mvvm.data.repo.AppConstants.YYYYMMDD
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toDateLong(): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val l = LocalDate.parse(this, DateTimeFormatter.ofPattern(YYYYMMDD))
        l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    } else {
        val date = SimpleDateFormat(YYYYMMDD, Locale.getDefault()).parse(this)
        (date?.time) as Long
    }
}