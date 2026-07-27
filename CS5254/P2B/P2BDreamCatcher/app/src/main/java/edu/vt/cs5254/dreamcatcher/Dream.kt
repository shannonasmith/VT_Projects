package edu.vt.cs5254.dreamcatcher

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

@Entity(tableName = "dream")
data class Dream(
    @PrimaryKey val id: UUID = UUID.randomUUID(), // 12.10
    val title: String = "",
    val lastUpdated: Date = Date(),
) {
    @Ignore // 12.10
    var entries = listOf(DreamEntry(kind = DreamEntryKind.CONCEIVED, dreamId = id))
    val isFulfilled get() = entries.any { it.kind == DreamEntryKind.FULFILLED }
    val isDeferred get() = entries.any { it.kind == DreamEntryKind.DEFERRED }
}

@Entity(tableName = "dream_entry") // 12.10
data class DreamEntry(
    @PrimaryKey @ColumnInfo(name = "entryId") val id: UUID = UUID.randomUUID(), // 12.10
    val text: String = "",
    val kind: DreamEntryKind,
    val dreamId: UUID
)

enum class DreamEntryKind {
    CONCEIVED,
    DEFERRED,
    FULFILLED,
    REFLECTION
}