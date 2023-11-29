package dev.login.jwtlogin.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.login.jwtlogin.entity.Position;
import dev.login.jwtlogin.entity.PositionType;
import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/update")
public class UpdateUser {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;
    @PutMapping("/position")
    public ResponseEntity<String> updatePosition(
            @RequestParam Long id,
            @RequestBody String newPosition) {

        try {
            // Konvertera JSON-strängen till ett JsonNode-objekt
            JsonNode newPositionJson = objectMapper.readTree(newPosition);

            // Extrahera värdet av "newPosition" från JsonNode
            String newPositionValue = newPositionJson.get("newPosition").asText();

            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                User updateUser = user.get();
                System.out.println("Nuvarande position: " + updateUser.getPosition());

                Optional<Position> position = userService.getPosition(PositionType.valueOf(newPositionValue.toUpperCase()));
                if (position.isPresent()) {
                    System.out.println("Ny position: " + position.get());
                    updateUser.setPosition(position.get());
                    userService.saveUser(updateUser);
                    System.out.println("Användarens position uppdaterad");
                    return new ResponseEntity<>("Användarens position uppdaterad", HttpStatus.OK);
                } else return new ResponseEntity<>("Position ej hittad", HttpStatus.BAD_REQUEST);

            } else {
                System.out.println("Användare ej hittad");
                return new ResponseEntity<>("Användare ej hittad", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) { // illegal "no enum konstant
            e.printStackTrace();
            return new ResponseEntity<>("Ogiltig JSON-payload, kontrollera stavning.", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/salary")
    public ResponseEntity<String> updateSalary(
            @RequestParam Long id,
            @RequestBody int salary) {

        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            user.get().setSalary(salary);
            userService.saveUser(user.get());
            return new ResponseEntity<>("Lön uppdaterad. ", HttpStatus.OK);
        } else if (!user.isPresent()){
            return new ResponseEntity<>("Fel med att uppdatera lön", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Kontrollera att din payload är korrekt. ", HttpStatus.BAD_REQUEST);

    }

}
