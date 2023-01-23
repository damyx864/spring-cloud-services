package dev.demo.jwt.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


    @GetMapping("/secure1")
    @PreAuthorize("hasRole('users-manager')")
    public Mono<ResponseEntity<String>> secure1(@AuthenticationPrincipal Jwt principal) {

        JSONObject realms = (JSONObject) principal.getClaims().get("realm_access");
        JSONArray roles = (JSONArray) realms.get("roles");

        LOGGER.debug("secure - {} {}", principal,  roles.toString());

        return Mono.just("This is secure1!").map(ResponseEntity::ok);
    }

    @GetMapping("/secure2")
    @PreAuthorize("hasRole('user-api')")
    public Mono<ResponseEntity<String>> secure2(@AuthenticationPrincipal Jwt principal) {
        JSONObject realms = (JSONObject) principal.getClaims().get("realm_access");
        JSONArray roles = (JSONArray) realms.get("roles");

        LOGGER.debug("secure - {} {}", principal,  roles.toString());

        return Mono.just("This is secure2!").map(ResponseEntity::ok);
    }
}
