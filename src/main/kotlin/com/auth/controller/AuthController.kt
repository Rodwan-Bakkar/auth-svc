package com.auth.controller

import com.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {
    data class AuthRequest(
        @field:Email(message = "Invalid email format.")
        val email: String,
        @field:Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{9,}\$",
            message = """
                Password must be at least 9 characters long and contain at least one digit, uppercase and lowercase 
                character.""",
        )
        val password: String,
    )

    data class RefreshRequest(
        val refreshToken: String,
    )

    @Operation(summary = "Register a new user", description = "Register a new user")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @Valid @RequestBody body: AuthRequest,
    ): UserResponse {
        val newUser = authService.register(body.email, body.password)
        return UserResponse(
            id = newUser.id,
            email = newUser.email,
            createdAt = newUser.createdAt,
        )
    }

    @Operation(summary = "Login with credentials", description = "Login with email and password")
    @PostMapping("/login")
    fun login(
        @RequestBody body: AuthRequest,
    ): AuthService.TokenPair = authService.login(body.email, body.password)

    @Operation(summary = "Refresh tokens", description = "Refresh both access and refresh tokens via refresh token")
    @PostMapping("/refresh")
    fun refresh(
        @RequestBody body: RefreshRequest,
    ): AuthService.TokenPair = authService.refresh(body.refreshToken)
}