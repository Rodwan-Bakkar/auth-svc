package com.auth

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthApplication

fun main(args: Array<String>) {
	// Load environment variables
	Dotenv.configure()
		.ignoreIfMissing()
		.load()
		.entries().forEach { entry ->
			System.setProperty(entry.key, entry.value)
		}
	runApplication<AuthApplication>(*args)
}
