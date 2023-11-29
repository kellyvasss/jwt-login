package dev.login.jwtlogin.service;


import dev.login.jwtlogin.repository.RoleRepository;
import dev.login.jwtlogin.repository.UserRepository;
import dev.login.jwtlogin.util.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.login.jwtlogin.entity.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public User registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = new User(username, encodedPassword, authorities);
        return userRepository.save(user);
    }

    public LoginResponseDTO loginUser(String username, String password){

        try {
            // Kontrollera om användaren finns i databasen
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                // Hämta användaren från databasen
                User user = optionalUser.get();

                // Kontrollera lösenordet
                if (passwordEncoder.matches(password, user.getPassword())) {
                    // Lösenordet är korrekt, generera JWT-token
                    Authentication auth = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, password)
                    );
                    String token = tokenService.generateToken(auth);
                    return new LoginResponseDTO(user, token);
                } else {
                    throw new RuntimeException("Felaktigt lösenord");
                }
            } else {
                // Användaren finns inte i databasen, skicka vidare till registrering
                registerUser(username, password);
                loginUser(username, password);
            }

        } catch (AuthenticationException e) {
            throw new RuntimeException("Misslyckades med authentisering.");
        }
        return null;}

}
