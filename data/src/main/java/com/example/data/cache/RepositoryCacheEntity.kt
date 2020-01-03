package com.example.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SyncTimestamp")
data class SyncTimestamp(@PrimaryKey @ColumnInfo(name = "timestamp") val timestamp: Long)

@Entity(tableName = "RepositoryCacheEntity")
data class RepositoryCacheEntity(
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "language")
    val language: String?,
    @ColumnInfo(name = "languageColor")
    val languageColor: String?,
    @ColumnInfo(name = "stars")
    val stars: Int,
    @ColumnInfo(name = "forks")
    val forks: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
) {


}