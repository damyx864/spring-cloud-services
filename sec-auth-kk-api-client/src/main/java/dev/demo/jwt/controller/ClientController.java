package dev.demo.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class ClientController {

    @Autowired
    WebClient webClient;

    @Value("${secured-service-url}")
    String uri;

    @GetMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    public String getSecured() {

        final Mono<String> result = webClient.get().uri(uri)
                .retrieve().bodyToMono(String.class);

        return result.block();
    }

}
