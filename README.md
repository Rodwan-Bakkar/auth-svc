
# Auth Service

This is a Spring Boot application written in Kotlin that handles **user authentication** with JWT tokens.

## Features

- Sign-up and Sign-in
- Password hashing
- JWT-based authentication
- PostgreSQL database
- Flyway for database migrations
- jOOQ for type-safe SQL queries

## Database Schema

- `users` table: stores user id, email, and hashed password
- `refresh_tokens` table: stores refresh tokens with expiration

## Sequence Diagrams

### User Registration

The sequence diagram below shows the flow for **registering a new user**:

![User Registration](https://www.plantuml.com/plantuml/svg/XPJ1Rjim38RlUWeYfnPOqMpOPJuCNP82ssKDakm1E9cjXIcMHzBj-lQp1Bc2ZHWDa2YIlv_yIGg-4qNM_kmDMWqCluJOTCZghEkmLLXzwxNPXbOvU4-y0XGOjvVG8_6pivIu_7yAnGm7we8u3VoQkBATejzHcllMXZhdZNjYKwFYRnI2rJw8FZ49x3OH61PZOdou-Gh5P0NxX-C9Rd6mTylqv4I9h-YCpd-03aLU0jVNfWYYF7klO5cGaGdzZDxLgBJFr3QSEq__dBvU5I5wVGi3haqQ0m1ZcBbzRJYymEVr6ZPOmu7-zYIQ15FFiVHeM7jkWPW36_92yz2btHNOXkmVS8-GgeF1gQWaGJGvFSoV8fzKdjFsGfpHT-lv4ayVlREQ5UyNbC1Sr3n7lXyLDCDQsZsI-V74T2If7eULoSLckLQ8DRdAjs4Mu7gXIRjD1Jz-7kyFfs4vFQGcbORkDd0ponI1d8xgYq4lj7xa5sOm6q8MIMyjYLmSmDi4Fgq_mfO9HpFKrYP-xuQV-Exu3m00)

> Diagram generated from [`docs/diagrams/sequence/user-registration.puml`](docs/diagrams/sequence/user-registration.puml)

## Getting Started

1. Clone the repository: