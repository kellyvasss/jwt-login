package dev.login.jwtlogin.controller;

import dev.login.jwtlogin.entity.Position;
import dev.login.jwtlogin.entity.PositionType;
import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.repository.PositionRepository;
import dev.login.jwtlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/get")
public class PublicController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String getOne(@RequestParam String username, Authentication authentication) {
        try {
            Optional<User> user = userService.getOne(username);
            if (authentication.getName().equals(username) && user.isPresent()) {
                return String.format("Username: %s\nLön: %d",
                        user.get().getUsername(),
                        user.get().getSalary());
            } else return "Du kan enbart få information om dig själv, använd sökparameter: " + authentication.getName();
        } catch (NullPointerException | NoSuchElementException e) {
            return "Kontrollera sökparametern. HINT -> (localhost:8080/get?username={ditt_värde})";
        }

    }
    @GetMapping("")
    public String getAll() {
        List<User> allUsers = userService.getAllUsers();
        String result = "ALLA ANVÄNDARE:";
        for (User user : allUsers) {
            result += String.format("\nUsername: %s\n", user.getUsername());
        } return result;
    }
}
