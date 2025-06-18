package com.andef.itcourses.design

import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.Locale

private val russianMonths = arrayOf(
    "Января", "Февраля", "Марта", "Апреля",
    "Мая", "Июня", "Июля", "Августа",
    "Сентября", "Октября", "Ноября", "Декабря"
)

val dateFormatter = DateTimeFormatterBuilder()
    .appendPattern("dd ")
    .appendText(ChronoField.MONTH_OF_YEAR, russianMonths.mapIndexed { index, name ->
        (index + 1).toLong() to name
    }.toMap())
    .appendPattern(" yyyy")
    .toFormatter()
    .withLocale(Locale("ru"))