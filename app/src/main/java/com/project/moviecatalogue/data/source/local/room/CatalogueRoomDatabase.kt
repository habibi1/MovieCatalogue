package com.project.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.moviecatalogue.data.source.local.entity.DetailMovieEntity
import com.project.moviecatalogue.data.source.local.entity.DetailTvShowEntity

@Database(
    entities = [DetailMovieEntity::class,
        DetailTvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueRoomDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueRoomDatabase? = null

        fun getInstance(context: Context): CatalogueRoomDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueRoomDatabase::class.java,
                    "note_database.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}