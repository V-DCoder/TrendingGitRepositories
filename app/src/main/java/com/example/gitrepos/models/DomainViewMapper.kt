package com.example.gitrepos.models

class DomainViewMapper {
    fun mapRepositories(list: List<com.example.domain.entity.Repository>): List<Repository> {

        val repositoryList = ArrayList<Repository>()

        list.forEach {

            repositoryList.add(
                Repository(
                    it.author,
                    it.name,
                    it.avatar,
                    it.description,
                    it.language,
                    it.languageColor,
                    it.stars,
                    it.forks
                )
            )
        }
        return repositoryList

    }


}