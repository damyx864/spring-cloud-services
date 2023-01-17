package dev.demo.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    // @PreAuthorize("hasAuthority('SCOPE_read')")
    @GetMapping("/secure")
    @ResponseStatus(HttpStatus.OK)
    public String secure() {
        LOGGER.debug("secure");

        return "This is secured!";
    }
}
