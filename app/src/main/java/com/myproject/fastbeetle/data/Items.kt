package com.myproject.fastbeetle.data


data class User(val items: List<Items>?)



data class Items(
    val emailId: String?,
    val lastName: String?,
    val imageUrl: String?,
    val firstName: String?
)

data class EmailObject(
    val emailId: String
)