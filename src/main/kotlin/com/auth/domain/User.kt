package com.auth.domain

import java.util.*

data class User(
    val id: UUID? = null,
    val email: String,
    val hashedPassword: String
) {}
