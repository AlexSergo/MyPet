package com.example.mypet.presentation

import com.google.type.DateTime
import java.text.SimpleDateFormat
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

object StringDate {

    private val monthDate = SimpleDateFormat("MMMM")
    private val yearDate = SimpleDateFormat("yyyy")
    private val numbersOfMonth = mutableMapOf<String, String>(
        Pair("01", "Января"), Pair("02", "Февраля"), Pair("03", "Марта"),
        Pair("04", "Апреля"), Pair("05", "Мая"), Pair("06", "Июня"),
        Pair("07", "Июля"), Pair("08", "Августа"), Pair("09", "Сентября"),
        Pair("10", "Октября"), Pair("11", "Ноября"), Pair("12", "Декабря"))

    fun getToday(): String{
        val calendar = Calendar.getInstance()
        val result = StringBuilder()
            .append(Calendar.DAY_OF_MONTH).append(" ")
            .append(monthDate.format(calendar.time)).append(" ")
            .append(yearDate.format(calendar.time))
        return result.toString()
    }

    fun getPreviousDate(current: String, count: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(current.substring(0,current.indexOf(" ")).toInt(), -count)
        val date = calendar.time
        val result = StringBuilder()
            .append(date.toString().substring(8, 10)).append(" ")
            .append(monthDate.format(calendar.time)).append(" ")
            .append(yearDate.format(calendar.time))
        return result.toString()
    }
}
