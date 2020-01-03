package com.example.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SyncTimestamp::class, RepositoryCacheEntity::class], version = 1)
abstract class LocalCache : RoomDatabase() {

    abstract fun repositoryDAO(): RepositoryDAO

    companion object {
        private var INSTANCE: LocalCache? = null

        fun getLocalCache(context: Context): LocalCache {
            synchronized(LocalCache::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalCache::class.java,
                        "LocalCache"
                    ).build()
                }
            }

            return INSTANCE!!
        }

    }

}