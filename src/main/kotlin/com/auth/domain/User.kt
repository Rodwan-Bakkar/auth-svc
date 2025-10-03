package com.auth.domain

import java.time.Instant
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val hashedPassword: String,
    val createdAt: Instant = Instant.now(),
)