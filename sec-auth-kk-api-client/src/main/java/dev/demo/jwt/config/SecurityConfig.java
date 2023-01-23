package dev.demo.jwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;


@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.client.provider.keycloak.jwk-set-uri}")
    private String jwks;

    @Bean
    public SecurityWebFilterChain securityFilterChain(final ServerHttpSecurity  http) throws Exception {
        // @formatter:off
        return http
                //deal with non-browser req
                .csrf(csrf -> csrf.disable())
                // enable CORS
                .cors()
                //just in order to test from outside
                .and().authorizeExchange().anyExchange().permitAll()
                // make it stateless - do not use default ServerSecurityContextRepository
                .and().securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();

        // @formatter:on
    }

}