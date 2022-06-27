package com.thevarungupta.grocery_app.models

data class RegisterResponse(
    val data: User,
    val error: Boolean,
    val message: String
)

data class User(
    val _id: String? = null,
    val createdAt: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val mobile: String? = null,
    val password: String? = null
)

data class LoginResponse(
    val token: String,
    val user: User
)
