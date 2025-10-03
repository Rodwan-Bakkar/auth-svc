package com.auth.domain

import java.time.Instant
import java.util.UUID

data class RefreshToken(
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    val expiresAt: Instant,
    val hashedToken: String,
    val createdAt: Instant = Instant.now(),
)