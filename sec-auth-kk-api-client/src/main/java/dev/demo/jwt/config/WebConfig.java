package dev.demo.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .filter((request, next) -> next.exchange(withBearerAuth(request)))
                .build();
    }

    private static ClientRequest withBearerAuth(final ClientRequest request) {

        final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ClientRequest.from(request)
                .headers(header -> header.setBearerAuth(jwt.getTokenValue()))
                .build();
    }
}