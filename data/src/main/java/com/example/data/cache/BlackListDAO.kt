package com.example.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface RepositoryDAO {

    @Insert
    fun insertTimestamp(timestamp: SyncTimestamp): Single<Long>

    @Insert
    fun insertRepositoryJson(repositories: List<RepositoryCacheEntity>): Single<Unit>

    @Query("SELECT COUNT(*) from SyncTimestamp where :timestamp < timestamp")
    fun searchValidTimestamp(timestamp: Long): Single<Int?>

    @Query("SELECT * from SyncTimestamp")
    fun searchAllTimestamp(): List<SyncTimestamp?>

    @Query("DELETE from SyncTimestamp")
    fun deleteTimestamp(): Single<Int>

    @Query("DELETE from RepositoryCacheEntity")
    fun deleteRepositoryCache(): Single<Int>

    @Query("SELECT * from RepositoryCacheEntity")
    fun getRepositoryFromCache(): Single<List<RepositoryCacheEntity>>

    @Query("SELECT * from RepositoryCacheEntity")
    fun getAllRepositoryFromCache(): List<RepositoryCacheEntity>


}