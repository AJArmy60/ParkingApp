package com.ser210.parkingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Space::class], version = 1, exportSchema = false)
abstract class SpaceRoomDatabase : RoomDatabase() {

    abstract fun spaceDao(): SpaceDao

    companion object {

        @Volatile
        private var INSTANCE: SpaceRoomDatabase? = null

        fun getDatabase(context: Context): SpaceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpaceRoomDatabase::class.java,
                    "spaces_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}