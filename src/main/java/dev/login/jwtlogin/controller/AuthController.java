package dev.login.jwtlogin.controller;

import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.service.AuthenticationService;
import dev.login.jwtlogin.service.TokenService;
import dev.login.jwtlogin.util.dto.LoginResponseDTO;
import dev.login.jwtlogin.util.dto.RegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    public AuthController(TokenService tokenService, AuthenticationService authenticationService) {
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOGGER.debug("Token request från user '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOGGER.debug("Token genererad: {}", token);
        return token;
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.username(), body.password());
    }
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.username(), body.password());
    }
}
