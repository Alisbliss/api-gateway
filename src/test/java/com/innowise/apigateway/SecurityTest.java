package com.innowise.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class SecurityTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void login_isPublic() {
		webTestClient.post().uri("/api/auth/login")
				.exchange()
				.expectStatus().value(status -> assertThat(status).isNotEqualTo(401));
	}

	@Test
	void register_isPublic() {
		webTestClient.post().uri("/api/auth/register")
				.exchange()
				.expectStatus().value(status -> assertThat(status).isNotEqualTo(401));
	}

	@Test
	void refresh_isPublic() {
		webTestClient.post().uri("/api/auth/refresh")
				.exchange()
				.expectStatus().value(status -> assertThat(status).isNotEqualTo(401));
	}

	@Test
	void users_requiresAuth() {
		webTestClient.get().uri("/api/users/1")
				.exchange()
				.expectStatus().isUnauthorized();
	}

	@Test
	void cards_requiresAuth() {
		webTestClient.get().uri("/api/cards/1")
				.exchange()
				.expectStatus().isUnauthorized();
	}
}