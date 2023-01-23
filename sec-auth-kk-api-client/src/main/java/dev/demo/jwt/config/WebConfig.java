package dev.demo.jwt.config;

import com.nimbusds.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Value("${default-registration-client-id}")
    private String defaultClientRegistrationId;

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauthExchangeFilter =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations,
                        new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
        oauthExchangeFilter.setDefaultClientRegistrationId(defaultClientRegistrationId);

        return WebClient.builder()
                .filter(oauthExchangeFilter)
                .build();
    }

}