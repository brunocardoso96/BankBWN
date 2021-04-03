package com.example.bankaccentur.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse (
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
)