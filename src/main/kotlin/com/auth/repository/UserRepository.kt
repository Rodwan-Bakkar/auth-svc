package com.auth.repository

import com.auth.domain.User
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import com.auth.jooq.generated.tables.references.USERS
import java.util.UUID

@Repository
class UserRepository(private val dsl: DSLContext) {

    fun findByEmail(email: String): User? =
        dsl.selectFrom(USERS)
            .where(USERS.EMAIL.eq(email))
            .fetchOne()
            ?.map { record ->
                User(
                    id = record[USERS.ID]!!,
                    email = record[USERS.EMAIL]!!,
                    hashedPassword = record[USERS.HASHED_PASSWORD]!!,
                    createdAt = record[USERS.CREATED_AT]!!.toInstant(),
                )
            }

    fun findById(userId: UUID): User? =
        dsl.selectFrom(USERS)
            .where(USERS.ID.eq(userId))
            .fetchOne()
            ?.map { record ->
                User(
                    id = record[USERS.ID]!!,
                    email = record[USERS.EMAIL]!!,
                    hashedPassword = record[USERS.HASHED_PASSWORD]!!,
                    createdAt = record[USERS.CREATED_AT]!!.toInstant(),
                )
            }

    fun save(user: User): User {

        val record = dsl.insertInto(USERS)
            .set(USERS.ID, user.id)
            .set(USERS.EMAIL, user.email)
            .set(USERS.HASHED_PASSWORD, user.hashedPassword)
            .returning(USERS.CREATED_AT) // return generated column
            .fetchOne()!!

        return user.copy(
            createdAt = record[USERS.CREATED_AT]!!.toInstant()
        )
    }
}
