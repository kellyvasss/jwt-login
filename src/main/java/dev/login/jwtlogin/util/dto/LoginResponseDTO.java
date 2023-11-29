package dev.login.jwtlogin.util.dto;
import dev.login.jwtlogin.entity.User;

public record LoginResponseDTO (User user, String jwt){
}
