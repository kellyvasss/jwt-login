package dev.login.jwtlogin.controller;

import dev.login.jwtlogin.entity.PositionType;
import dev.login.jwtlogin.entity.User;
import dev.login.jwtlogin.service.UserService;
import dev.login.jwtlogin.util.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("economy")
public class EconomyController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String getSalariesAndNamesByPosition(@RequestParam PositionType positionType) {
        String result = positionType.toString();
        int totalSalaries = 0;
        List<Object[]> namesAndSalaries = userService.getNamesAndSalariesByPosition(positionType);
        for(Object[] employee : namesAndSalaries) {
            result += String.format("\nName: %s\nSalary: %d\n",
                    employee[0],
                    (int) employee[1]);
            totalSalaries += (int) employee[1];
        } return result + "\nTOTAL LÖN: " + totalSalaries;
    }

    @GetMapping("")
    public String getAll() {
        int BOSS = Counter.getAll(userService.getNamesAndSalariesByPosition(PositionType.BOSS));
        int VICE_BOSS = Counter.getAll(userService.getNamesAndSalariesByPosition(PositionType.VICE_BOSS));
        int SLAVE = Counter.getAll(userService.getNamesAndSalariesByPosition(PositionType.SLAVE));
        return "Totala lönesummor för alla anställda: " + (BOSS + VICE_BOSS + SLAVE);
    }



}
