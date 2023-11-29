package dev.login.jwtlogin.service;

import dev.login.jwtlogin.entity.Position;
import dev.login.jwtlogin.entity.PositionType;
import dev.login.jwtlogin.entity.Role;
import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.repository.PositionRepository;
import dev.login.jwtlogin.repository.RoleRepository;
import dev.login.jwtlogin.repository.UserRepository;
import dev.login.jwtlogin.util.Reader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CommandLineService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PositionRepository positionRepository;
    private final Reader reader;

    public CommandLineService(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            PositionRepository positionRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.positionRepository = positionRepository;
        this.reader = new Reader("src/main/resources/Login");

    }

    @Transactional
    public void initializeData() {
        if (roleRepository.findByAuthority("ADMIN").isPresent()) {
            return;
        }

        Role admin = roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(admin);

        // Kontroll om det finns positioner i tabellen
        if (!positionRepository.findByPositionType(PositionType.BOSS).isPresent()) {
            Position bossPosition = new Position(PositionType.BOSS);
            Position viceBossPosition = new Position(PositionType.VICE_BOSS);
            Position slave = new Position(PositionType.SLAVE);
            positionRepository.save(bossPosition);
            positionRepository.save(viceBossPosition);
            positionRepository.save(slave);



            String username = reader.getValue("username");
            String password = reader.getValue("password");


            System.out.println(username + password);


            // Lägg till användare i tabellen
            User adminUser = new User(username,
                    passwordEncoder.encode(password),
                    roleSet);

            Optional<Position> boss = positionRepository.findByPositionType(PositionType.BOSS);
            boss.ifPresent(adminUser::setPosition);

            userRepository.save(adminUser);
        }
    }
}