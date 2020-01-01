package com.example.domain.entity

data class Repository(

    val author: String,
    val name: String,
    val avatar: String,
    val description: String,
    val language: String?,
    val languageColor: String?,
    val stars: Int,
    val forks: Int
)
