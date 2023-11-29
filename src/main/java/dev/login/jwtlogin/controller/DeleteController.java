package dev.login.jwtlogin.controller;

import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    private UserService userService;

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestParam String username, Authentication authentication) {

        if(authentication.getName().equals(username)) {
            Long id = userService.getOne(username).get().getId();
            userService.deleteOne(id);
            return new ResponseEntity<>("Användare raderad.", HttpStatus.OK);
        } else return new ResponseEntity<>("Du kan enbart radera dig själv. ", HttpStatus.BAD_REQUEST);


    }
}
