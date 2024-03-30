package com.radlance.presentation.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertTimeStampToTime(timeStamp: Long): String {
    val instant = Instant.ofEpochSecond(timeStamp)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return localDateTime.format(formatter)
}