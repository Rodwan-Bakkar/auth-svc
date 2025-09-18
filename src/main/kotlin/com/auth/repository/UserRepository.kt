package com.auth.repository

import com.auth.domain.User
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import com.auth.jooq.generated.tables.references.USERS
import java.util.*

@Repository
class UserRepository(private val dsl: DSLContext) {

    fun findByEmail(email: String): User? =
        dsl.selectFrom(USERS)
            .where(USERS.EMAIL.eq(email))
            .fetchOne()
            ?.map { record ->
                User(
                    id = record[USERS.ID],
                    email = record[USERS.EMAIL].toString(),
                    hashedPassword = record[USERS.HASHED_PASSWORD].toString(),
                )
            }

    fun save(user: User): User {
        val userId = user.id ?: UUID.randomUUID()

        dsl.insertInto(USERS)
            .set(USERS.ID, userId)
            .set(USERS.EMAIL, user.email)
            .set(USERS.HASHED_PASSWORD, user.hashedPassword)
            .execute()

        return user.copy(id = userId)
    }
}