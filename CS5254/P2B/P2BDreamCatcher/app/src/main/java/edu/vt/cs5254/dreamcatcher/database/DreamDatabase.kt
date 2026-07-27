package edu.vt.cs5254.dreamcatcher.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.vt.cs5254.dreamcatcher.Dream
import edu.vt.cs5254.dreamcatcher.DreamEntry

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

@Database(
    entities = [Dream::class, DreamEntry::class],
    version = 1,
    exportSchema = false
) // ch 12.11
@TypeConverters(DreamTypeConverters::class) // ch 12.13
abstract class DreamDatabase : RoomDatabase() { // ch 12.11
    abstract fun dreamDao(): DreamDao // 12.16
}

