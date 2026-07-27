package edu.vt.cs5254.dreamcatcher.database

import androidx.room.TypeConverter
import java.util.*

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

class DreamTypeConverters { // 12.12

    @TypeConverter
    fun fromDate(date: Date) = date.time
    /*fun fromDate(date: Date): Long {
        return date.time
    }*/

    @TypeConverter
    fun toDate(millis: Long) = Date(millis)
    /*
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
     */
}