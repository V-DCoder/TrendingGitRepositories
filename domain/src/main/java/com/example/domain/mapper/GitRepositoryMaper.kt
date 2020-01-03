package com.example.domain.mapper

import com.example.data.cache.RepositoryCacheEntity
import com.example.data.network.models.Repository

class GitRepositoryMapper {

    fun mapNetwornRepositoryToDomain(list: List<Repository>): List<com.example.domain.entity.Repository> {
        val domainList = arrayListOf<com.example.domain.entity.Repository>()
        list.forEach {
            domainList.add(
                com.example.domain.entity.Repository(
                    it.author,
                    it.name,
                    it.avatar,
                    it.description,
                    it.language ?: "NA",
                    it.languageColor,
                    it.stars,
                    it.forks
                )
            )
        }
        return domainList
    }

    fun mapCacheRepositoryToDomain(list: List<RepositoryCacheEntity>): List<com.example.domain.entity.Repository>? {
        val domainList = arrayListOf<com.example.domain.entity.Repository>()
        list.forEach {
            domainList.add(
                com.example.domain.entity.Repository(
                    it.author,
                    it.name,
                    it.avatar,
                    it.description,
                    it.language ?: "NA",
                    it.languageColor,
                    it.stars,
                    it.forks
                )
            )
        }
        return domainList
    }

    fun mapDomainToCacheRepository(list: List<com.example.domain.entity.Repository>): List<RepositoryCacheEntity> {
        val localCache = arrayListOf<RepositoryCacheEntity>()
        list.forEach {
            localCache.add(
                RepositoryCacheEntity(
                    it.author,
                    it.name,
                    it.avatar,
                    it.description,
                    it.language ?: "NA",
                    it.languageColor,
                    it.stars,
                    it.forks
                )
            )
        }
        return localCache
    }
}