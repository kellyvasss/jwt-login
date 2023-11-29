package dev.login.jwtlogin.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer id;
    private String authority;
    public Role() {
    }
    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
