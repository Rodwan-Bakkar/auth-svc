package com.auth.repository

import com.auth.domain.RefreshToken
import com.auth.jooq.generated.tables.references.REFRESH_TOKENS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.ZoneOffset

@Repository
class RefreshTokenRepository(private val dsl: DSLContext) {

    fun save(refreshToken: RefreshToken): RefreshToken {

        val record = dsl.insertInto(REFRESH_TOKENS)
            .set(REFRESH_TOKENS.ID, refreshToken.id)
            .set(REFRESH_TOKENS.USER_ID, refreshToken.userId)
            .set(REFRESH_TOKENS.EXPIRES_AT, refreshToken.expiresAt.atOffset(ZoneOffset.UTC))
            .set(REFRESH_TOKENS.HASHED_TOKEN, refreshToken.hashedToken)
            .returning(REFRESH_TOKENS.CREATED_AT) // return generated column
            .fetchOne()!!

        return refreshToken.copy(
            createdAt = record[REFRESH_TOKENS.CREATED_AT]!!.toInstant()
        )
    }
}
