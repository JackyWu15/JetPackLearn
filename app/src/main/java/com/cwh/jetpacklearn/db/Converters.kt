package com.cwh.jetpacklearn.db

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by cwh on 2019/11/12 0012.
 * 功能:
 */
class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}