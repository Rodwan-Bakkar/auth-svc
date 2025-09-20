
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

### User Login

The sequence diagram below shows the flow for **user login**:

![User Login](https://img.plantuml.biz/plantuml/svg/bPHjRvim4CVV-rESyYgTqdo0IDkQ42CMTMi6H7qvUN09reYDRDEittwsUGYmjbij0TBTx-x-TpQ-quOfKvzBmZ8Z5Sn29OL1aS-0QGWfgQoRPxnYmi1iNPiYi7ubonALHzR1tqY2webdsFYJiV-WKSLOISrjkMUF7E8n4Z5TK979l2qHJLJiBcPOOPUGd1dsasc4sLvgSrAOVB_tlkrc71hZKQ4kKlaBnKH5dCQ4X1HkFzcULb3A4nTpF3DUBg1YMb-aoc_8El10yYf0reaZ3l4AZbpacsVgY8Qx8OVOERUR5IJqdWOfV80mVlWAjPsAXiU8nXGy-l4pOQNnTX3Im57M8YTWrtO3jsq1KPUbjxcqhQpUrkXmhPX2oGjiM1uep54OpafDVsTO6Iv5mmO-FgJ_Wh6qG_QYNXJaDdSggBSrWY9RuynCLg2UTrDR-9JBmkuxvlj-b2x83Q73uCor3_KUjoBQbpbQdTWxtjFykmRWO3-4gwYccPSq6LLF9Usi-OG25JEupZBKsf_5kHy0RvjsQvVqQTYLUoFRy6Y_cKuDm4a--m-iGBCdx18iHlGrdGMxW_pbMqBZr7xIXmx-OHojvylbyXhI7fE-H3vEreWRwMbtq9VUCwxcWq6y9comaVyTPsyxufux-t8tuHy0)

> Diagram generated from [`docs/diagrams/sequence/user-login.puml`](docs/diagrams/sequence/user-registration.puml)

## Getting Started

1. Clone the repository: