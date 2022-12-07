package org.volix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.authorizeRequests()
		.anyRequest()
		.permitAll()
		/*
		.authenticated()
		.and()
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)*/;
		
		return http.build();
	}

}