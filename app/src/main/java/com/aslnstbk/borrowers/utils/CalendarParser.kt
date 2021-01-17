package com.aslnstbk.borrowers.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import java.util.Locale

const val SIMPLE_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy"

class CalendarParser {

    fun getCurrentTime(): Date = Calendar.getInstance().time

    fun getParsedDate(dateString: String?): String {
        val calendar = parseCalendar(dateString)

        return parseTime(calendar)
    }

    fun getShortDate(dateString: String?): String {
        val calendar = parseCalendar(dateString)

        val year = calendar.get(Calendar.YEAR)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month: String = parseMonth(calendar.get(Calendar.MONTH))

        return "$day $month - $year"
    }

    private fun parseCalendar(dateString: String?): Calendar {
        val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.ENGLISH)
        val calendar = Calendar.getInstance()

        try {
            calendar.time = sdf.parse(dateString)
        } catch (e: Exception) {}

        return calendar
    }

    private fun parseMonth(countOfMonth: Int): String = when(countOfMonth) {
        Calendar.JANUARY -> "January"
        Calendar.FEBRUARY -> "February"
        Calendar.MARCH -> "March"
        Calendar.APRIL -> "April"
        Calendar.MAY -> "May"
        Calendar.JUNE -> "June"
        Calendar.JULY -> "July"
        Calendar.AUGUST -> "August"
        Calendar.SEPTEMBER -> "September"
        Calendar.OCTOBER -> "October"
        Calendar.NOVEMBER -> "November"
        Calendar.DECEMBER -> "December"
        else -> ""
    }

    private fun parseTime(calendar: Calendar): String {
        val month: String = parseMonth(calendar.get(Calendar.MONTH))
        val day: String = getCorrectTimeString(calendar.get(Calendar.DAY_OF_MONTH))
        val hour: String = getCorrectTimeString(calendar.get(Calendar.HOUR_OF_DAY))
        val minute: String = getCorrectTimeString(calendar.get(Calendar.MINUTE))
        val second: String = getCorrectTimeString(calendar.get(Calendar.SECOND))

        return "$day $month $hour:$minute:$second"
    }

    private fun getCorrectTimeString(time: Int): String = when {
        time > 9 -> time.toString()
        else -> "0$time"
    }
}