# Draftly — Gmail AI Reply Agent (Spring Boot)

## Summary
Draftly is a backend service that fetches emails, uses an LLM (OpenAI) to generate draft replies, and allows a user to review/approve/send replies via the Gmail API. This project demonstrates Spring Boot, REST APIs, JWT auth, WebClient integration with OpenAI, and persistence.

## Stack
- Java 17, Spring Boot 3.x
- Spring Data JPA (H2 for dev; Postgres for prod)
- Spring Security + JWT
- WebClient for OpenAI API
- OpenAPI (Swagger UI)

## Setup (dev)
1. Clone repo
2. Set env vars:
   - `export OPENAI_API_KEY="sk-..."` (Linux/mac)
   - `export JWT_SECRET="replace_with_secure_secret"`
   - `export GOOGLE_CLIENT_ID="..."` and `export GOOGLE_CLIENT_SECRET="..."`
3. Run (uses embedded H2 by default):
   - `./mvnw spring-boot:run`
4. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Setup (Postgres)
1. Start DB: `docker compose up -d`
2. Set env:
   - `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/draftlydb`
   - `SPRING_DATASOURCE_USERNAME=draftly`
   - `SPRING_DATASOURCE_PASSWORD=draftlypass`
3. Run as above.

## Endpoints (examples)
- `POST /api/auth/register` { "email": "you@example.com", "password": "pass" } -> returns token
- `POST /api/auth/login` -> token
- `GET /api/gmail/fetch?limit=3` (auth required)
- `POST /api/drafts/generate` { "sender","subject","body","tone" } -> generate draft
- `GET /api/drafts` -> list drafts
- `POST /api/drafts/{id}/approve` -> approve & send (mock or real via Gmail)

## Demo script (3-5 min)
1. Start server. Open Swagger.
2. Register a user & login to get JWT.
3. `GET /api/gmail/fetch` to create mock emails or fetch real Gmail emails after OAuth.
4. `POST /api/drafts/generate` to generate AI draft (show returned text).
5. `POST /api/drafts/{id}/approve` to send (mock or real).
6. Show DB rows (H2 Console) or logs.
7. Conclude with future work: real Gmail OAuth, token encryption, production-grade retry/backoff.

## Security & production notes
- **Do not** commit `OPENAI_API_KEY`, `GOOGLE_CLIENT_SECRET`, or `JWT_SECRET` to git.
- Hash passwords (BCrypt) — implemented.
- Implement RBAC & proper `UserDetailsService`.
- Replace mocked Gmail service with real OAuth2 flow + Gmail REST API.
- Add retry/backoff for API calls and better error handling.
