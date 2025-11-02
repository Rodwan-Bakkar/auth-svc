package com.auth.unit.services

import com.auth.domain.User
import com.auth.repository.RefreshTokenRepository
import com.auth.repository.UserRepository
import com.auth.service.AuthService
import com.auth.service.HashEncoder
import com.auth.service.JwtService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class AuthServiceTest {
    private val jwtService: JwtService = mock()
    private val userRepository: UserRepository = mock()
    private val hashEncoder: HashEncoder = mock()
    private val refreshTokenRepository: RefreshTokenRepository = mock()

    private val authService =
        AuthService(
            jwtService,
            userRepository,
            hashEncoder,
            refreshTokenRepository,
        )

    @Test
    fun `register should save and return a new user`() {
        // Arrange
        val email = "test@example.com"
        val password = "password123"
        val hashedPassword = "hashedPassword123"

        whenever(userRepository.findByEmail(email)).thenReturn(null)
        whenever(hashEncoder.encode(password)).thenReturn(hashedPassword)
        whenever(userRepository.save(any())).thenAnswer { it.arguments[0] }

        // Act
        val result = authService.register(email, password)

        // Assert
        verify(userRepository).findByEmail(email)
        verify(hashEncoder).encode(password)
        verify(userRepository).save(any())

        assertEquals(email, result.email)
        assertEquals(hashedPassword, result.hashedPassword)
    }

    @Test
    fun `register should throw Conflict if user already exists`() {
        // Arrange
        val email = "existing@example.com"
        val password = "password123"
        val existingUser = User(email = email, hashedPassword = "oldHash")

        whenever(userRepository.findByEmail(email)).thenReturn(existingUser)

        // Act & Assert
        val exception =
            assertThrows<ResponseStatusException> {
                authService.register(email, password)
            }

        assertEquals(HttpStatus.CONFLICT, exception.statusCode)
        assertEquals("A user with that email already exists.", exception.reason)

        verify(userRepository).findByEmail(email)
        verify(userRepository, never()).save(any())
        verify(hashEncoder, never()).encode(any())
    }
}