package com.example.riya.finalassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.riya.finalassignment.dao.ComicssDAO
import com.example.riya.finalassignment.entity.Comicss


@Database(
        entities = [(Comicss::class)],
        version = 2,
        exportSchema = false
)
abstract class comicDB : RoomDatabase() {
    abstract fun getProductDAO(): ComicssDAO

    companion object {
        @Volatile
        private var instance: comicDB? = null

        fun getInstance(context: Context): comicDB {
            if (instance == null) {
                synchronized(comicDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        comicDB::class.java,
                        "Comic Reader"
                ).build()
    }
}