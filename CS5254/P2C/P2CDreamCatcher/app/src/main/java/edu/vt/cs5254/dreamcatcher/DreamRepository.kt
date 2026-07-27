package edu.vt.cs5254.dreamcatcher

import android.content.Context
import androidx.room.Room
import edu.vt.cs5254.dreamcatcher.database.DreamDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

private const val DATABASE_NAME = "dream-database" // 12.20

@DelicateCoroutinesApi
class DreamRepository( // private constructor
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {

    private val database = Room.databaseBuilder(
        context.applicationContext,
        DreamDatabase::class.java,
        DATABASE_NAME
    )
        .createFromAsset(DATABASE_NAME) // calls dream-database from Assets folder --> See 12.22
        .build()

    // DLF: 12.21 AP Transform the DAO multimap into a list of dreams with their entries:
    fun getDreams(): Flow<List<Dream>> { // 12.25 remove suspend, return Flow
        val dreamMultiMap = database.dreamDao().getDreams()
        return dreamMultiMap.map { dreamMap -> // Q&A 1:02 --> dreamMultiMapFlow is same as dreamMultiMap
            dreamMap.keys.map { dream ->
                dream.apply { entries = dreamMap.getValue(dream) }
            }
        }
    }

    // DDF fetch: 12.21 AP Call the DAO @Transaction function, to get the dream and its entries:
    suspend fun getDream(id: UUID): Dream {
        return database.dreamDao().getDreamAndEntries(id)
    }

    // DDF update: 13.23 AP
    fun updateDream(dream: Dream) {
        coroutineScope.launch { // 13.25 AP
            database.dreamDao().updateDreamAndEntries(dream)
        }
    }

    // 15.6 DLF add new dream
    suspend fun addDream(dream: Dream) = database.dreamDao().insertDreamWithEntries(dream) // <-- make sure to call the transaction here
    // from here, go to DLVM...

    // DLF delete dream
    suspend fun deleteDream(dream: Dream) = database.dreamDao().deleteDreamWithEntries(dream) // <-- make sure to call the transaction here
    // from here, go to DLVM...

    // 12.17
    companion object {
        private var INSTANCE: DreamRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DreamRepository(context)
            }
        }

        fun get() = checkNotNull(INSTANCE) { "DreamRepository must be initialized" } // Q&A

    }

}