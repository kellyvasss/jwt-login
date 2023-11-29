package dev.login.jwtlogin.service;

import dev.login.jwtlogin.entity.Position;
import dev.login.jwtlogin.entity.PositionType;
import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.repository.PositionRepository;
import dev.login.jwtlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserRepository userRepository;
    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getOne(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Object[]> getNamesAndSalariesByPosition(PositionType positionType) {
        return userRepository.findNamesAndSalariesByPosition(positionType);
    }
    public Optional<Position> getPosition(PositionType positionType) {
        return positionRepository.findByPositionType(positionType);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Kunde inte hitta användare"));
    }
}
