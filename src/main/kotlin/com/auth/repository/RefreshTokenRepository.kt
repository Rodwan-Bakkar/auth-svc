package com.auth.repository

import com.auth.domain.RefreshToken
import com.auth.jooq.generated.tables.references.REFRESH_TOKENS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.ZoneOffset
import java.util.UUID

@Repository
class RefreshTokenRepository(private val dsl: DSLContext) {

    fun findByUserIdAndHashedToken(userId: UUID, hashedRefreshToken: String): RefreshToken? =
        dsl.selectFrom(REFRESH_TOKENS)
            .where(REFRESH_TOKENS.USER_ID.eq(userId))
            .and(REFRESH_TOKENS.HASHED_TOKEN.eq(hashedRefreshToken))
            .fetchOne()
            ?.map { record ->
                RefreshToken(
                    id = record[REFRESH_TOKENS.ID]!!,
                    userId = record[REFRESH_TOKENS.USER_ID]!!,
                    expiresAt = record[REFRESH_TOKENS.EXPIRES_AT]!!.toInstant(),
                    hashedToken = record[REFRESH_TOKENS.HASHED_TOKEN]!!,
                    createdAt = record[REFRESH_TOKENS.CREATED_AT]!!.toInstant(),
                )
            }

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

    fun deleteByUserIdAndHashedToken(userId: UUID, hashedRefreshToken: String) {
        dsl.deleteFrom(REFRESH_TOKENS)
            .where(REFRESH_TOKENS.USER_ID.eq(userId))
            .and(REFRESH_TOKENS.HASHED_TOKEN.eq(hashedRefreshToken))
            .execute()
    }
}
