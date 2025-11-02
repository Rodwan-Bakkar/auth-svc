package com.auth.integration

import com.auth.repository.UserRepository
import com.auth.service.AuthService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@SpringBootTest
@Transactional
class AuthServiceIntegrationTest(
    @Autowired private val authService: AuthService,
    @Autowired private val userRepository: UserRepository,
) {
    @Test
    fun `register should persist a new user`() {
        // when
        val savedUser = authService.register("test@example.com", "password123")

        // then
        val foundUser = userRepository.findByEmail("test@example.com")
        assertNotNull(foundUser)
        assertEquals(savedUser.email, foundUser?.email)
        assertNotEquals("password123", foundUser?.hashedPassword)
    }

    @Test
    fun `register should throw conflict if user already exists`() {
        // given
        authService.register("existing@example.com", "password")

        // when + then
        val exception =
            assertThrows(ResponseStatusException::class.java) {
                authService.register("existing@example.com", "password")
            }

        assertEquals("409 CONFLICT \"A user with that email already exists.\"", exception.message)
    }
}