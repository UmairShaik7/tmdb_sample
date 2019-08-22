package com.mvvm.data.repo.extentions

import android.os.Build
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.AppConstants.SERVER_DATE_FORMATE
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toDateLong(): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val l = LocalDate.parse(this, DateTimeFormatter.ofPattern(SERVER_DATE_FORMATE))
        l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    } else {
        val date = SimpleDateFormat(SERVER_DATE_FORMATE, Locale.getDefault()).parse(this)
        (date?.time) as Long
    }
}


fun String.getYear(): CharSequence {
    val date = SimpleDateFormat(SERVER_DATE_FORMATE, Locale.getDefault()).parse(this)
    val myFormat = SimpleDateFormat(AppConstants.YEAR_FORMATE, Locale.getDefault())
    return myFormat.format(date)


}
