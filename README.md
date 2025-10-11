# Auth Service

This is a Spring Boot application written in Kotlin that handles **user authentication** with JWT tokens.

## ✨ Features

- Sign-up and Sign-in
- Password hashing
- JWT-based authentication
- PostgreSQL database
- Flyway for database migrations
- jOOQ for type-safe SQL queries

[Visit the OpenAPI specs](https://auth-svc-qxq3.onrender.com/swagger-ui/index.html)
> ⚠️ **Note:** The project is hosted on Render, cold starts may take a few minutes, so please be patient 😄

## 🧑‍💻 Develop Locally

This guide provides the necessary steps to set up the **Auth Service** database and run the application using IntelliJ
IDEA.

### 1. Clone and Configure

- Clone the Repository.

- Create Environment File (`.env`):
  Create a file named `.env` in the root directory of the project and populate it with the following environment
  variables:

```bash
DB_URL=jdbc:postgresql://localhost:5432/auth_db
DB_USER=db_user
DB_PASSWORD=db_password
JWT_SECRET=****** Add your secret here ****
```

Gradle will read these variables on build time and use them for flyway migration and jOOQ generation.

### 2. Start the Database

1. Run `docker-compose up -d` to spin up the development database.

2. Run `docker ps` to confirm that the database container is up and running.

### 3. Run the Application

1. Using `IntelliJ` run `AuthApplication.kt`

2. Verify the migration was done successfully: connect to the PostgreSQL instance using a database client (e.g., DBeaver
   or psql) with the credentials from your `.env` file and double-check the tables were created successfully. Migration
   files live in `/resources/db/migration/`

You are now ready to develop locally 😄

## ⚖️ Code Style [WIP]

## 🧪 Testing [WIP]

## 📚 Develop the Docs

[Visit the docs](https://rodwan-bakkar.github.io/auth-svc/)

The documentation is written in **Markdown** and hosted on **GitHub Pages**. It is generated using **MkDocs** with
the **Material for MkDocs** theme and using **GitHub Actions** (Check `.github/workflows/deploy-docs.yml`).  
Currently, it mainly contains UML diagrams created with **PlantUML** using the **MkDocs PlantUML plugin**.

### Develop Locally

1. Make sure **Python** is installed on your machine.
2. Create a Python virtual environment, install the necessary dependencies and serve the docs locally:

```bash
python -m venv .venv
source .venv/bin/activate
pip install mkdocs mkdocs-material mkdocs-puml
mkdocs serve
```

> ⚠️ **Note:** UML diagrams are embedded in the Markdown files and generated using the **MkDocs PlantUML plugin**.  
> You can use [PlantUML Online Editor](https://editor.plantuml.com/) to create or edit these diagrams.

## ⚙️ Github Actions [WIP]

## 🚀 Deploy to Production [WIP]

## 🤝 How to Contribute [WIP]