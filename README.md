# api-gateway

API Gateway for the microservices project. Routes requests to `auth-service`, `user-service`, `order-service` and validates JWT via JWKS (RS256).

## Tech Stack

- Java 21, Spring Boot 4
- Spring Cloud Gateway (WebFlux, reactive)
- Spring Security (OAuth2 Resource Server, RS256 + JWKS)
- Docker, GitHub Actions
- WebTestClient (integration tests)

## Features

- Route `/api/auth/**` to `auth-service`
- Route `/api/users/**, /api/cards/**` to `user-service`
- Route `/api/orders/**` to `order-service`
- Route `/api/payments/**` to `payment-service`
- JWT validation via `jwk-set-uri` (RS256, JWKS from `auth-service`)
- Public endpoints: `/api/auth/login`, `/api/auth/register`, `/api/auth/refresh`
- Stateless (no session, no CSRF)
- Only gateway publishes port `8080` (others are internal)

## Run with Docker

### Clone repositories into one folder

```bash
mkdir microservices && cd microservices

git clone https://github.com/Alisbliss/auth-service.git     auth-service
git clone https://github.com/Alisbliss/user-service.git     user-service
git clone https://github.com/Alisbliss/order-service.git    order-service
git clone https://github.com/Alisbliss/payment-service.git  payment-service
git clone https://github.com/Alisbliss/api-gateway.git      api-gateway
git clone https://github.com/Alisbliss/infra.git            infra