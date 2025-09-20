package com.auth.controller

import java.time.Instant
import java.util.*

data class UserResponse(
    val id: UUID,
    val email: String,
    val createdAt: Instant,
)