package edu.vt.cs5254.dreamcatcher.database

import androidx.room.TypeConverter
import java.util.*

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

class DreamTypeConverters { // 12.12

    @TypeConverter
    fun fromDate(date: Date) = date.time

    @TypeConverter
    fun toDate(millis: Long) = Date(millis)
}