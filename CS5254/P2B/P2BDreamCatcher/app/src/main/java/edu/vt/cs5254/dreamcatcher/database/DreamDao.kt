package edu.vt.cs5254.dreamcatcher.database

import androidx.room.*
import edu.vt.cs5254.dreamcatcher.Dream
import edu.vt.cs5254.dreamcatcher.DreamEntry
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

@Dao
interface DreamDao {

    // DLF:
    @Query("SELECT * FROM dream d JOIN dream_entry e ON d.id = e.dreamId ORDER BY d.lastUpdated DESC") // 12.15
    fun getDreams(): Flow<Map<Dream, List<DreamEntry>>> // 12.24 remove suspend, return Flow<MultiMap> --> see Q&A

    // DDF:
    @Query("SELECT * FROM dream WHERE id=(:id)") // 12.15
    suspend fun getDream(id: UUID): Dream

    @Query("SELECT * FROM dream_entry where dreamId = (:dreamId)")
    suspend fun getEntriesForDream(dreamId: UUID): List<DreamEntry>

    @Transaction
    suspend fun getDreamAndEntries(id: UUID): Dream {
        return getDream(id).apply { entries = getEntriesForDream(id) }
    }

    // 13.22 AP
    @Update
    suspend fun updateDream(dream: Dream)

    @Insert
    suspend fun insertDreamEntry(dreamEntry: DreamEntry)

    @Query("DELETE FROM dream_entry where dreamId = (:id)")
    suspend fun deleteEntriesFromDream(id: UUID)

    @Transaction
    suspend fun updateDreamAndEntries(dream: Dream) {
        deleteEntriesFromDream(dream.id)
        dream.entries.forEach { insertDreamEntry(it) }
        updateDream(dream)
    }

}