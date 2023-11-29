package dev.login.jwtlogin.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Instant registered;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role_junction",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities = new HashSet<>();
    private int Salary;
    public Instant getRegistered() {
        return registered;
    }

    public void setRegistered(Instant registered) {
        this.registered = registered;
    }


    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }



    public User() {
    }

    public User(String username, String password, Set<Role> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        registered = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
