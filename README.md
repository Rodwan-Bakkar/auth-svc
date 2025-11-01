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

> ⚠️ **Note:** The project is hosted on [Render](https://render.com/), cold starts may take a few minutes, so please be
> patient 😄

## 🧑‍💻 Develop Locally

This guide provides the necessary steps to set up the **Auth Service** database and run the application using IntelliJ
IDEA.

### 1. Clone and Configure

- Clone the Repository.

- Create a file named `.env` in the root directory of the project and populate it with the following environment
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

## ⚖️ Code Style

This project follows the default Kotlin code style provided by JetBrains. You can find the configuration in the
`.editorconfig` file, which can be exported from IntelliJ. We use `Ktlint` to enforce this code style. The Ktlint check
runs as a step in the Pull Request Workflow and can fail the workflow if violations are detected.

Tip: You can automatically apply the code style defined in `.editorconfig` on save in IntelliJ by enabling the
corresponding setting in IntelliJ preferences.

## 🧪 Testing

- Unit tests: Validate core business logic in isolation using JUnit 5 and Mockito. Unit tests run automatically as part
  of the CI pipeline to ensure correctness and prevent regressions.
- Integration tests: [WIP]

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

## ⚙️ CI/CD (GitHub Actions)

GitHub Actions is used to automate two main workflows:

- Pull Request Workflow: Triggered when a new pull request is created. It builds the project, runs code style checks,
  executes tests, and ensures that the documentation builds successfully. Any failure in these steps will cause the
  workflow to fail, blocking the merge until the issues are resolved.

- Push Workflow: Triggered after a pull request is merged. It rebuilds the documentation (which should already pass, as
  validated in the PR workflow) and deploys it to GitHub Pages, publishing the generated files to the `gh-pages` branch.

## 🚀 Deploy to Production

The demo is hosted on the [Render](https://render.com/) Hobby tier. Render automatically triggers a new deployment after
each merge to the main branch.
The application uses a [Neon](https://neon.com/) PostgreSQL database for storage.

## 🤝 How to Contribute

Contributions are welcome! To get started:

- Fork this repository.
- Create a new branch from `main`.
- Make your changes and push them to your fork.
- Open a pull request from your forked branch to the `main` branch of this repository.

## 📬 Contact Me

If you run into any issues or need help, feel free to reach out:

- GitHub Issues: Open an issue in this repository
- Email: rodwan.bakkar@gmail.com
- Linkedin: [Rodwan Bakkar](https://www.linkedin.com/in/rodwan-bakkar/)
