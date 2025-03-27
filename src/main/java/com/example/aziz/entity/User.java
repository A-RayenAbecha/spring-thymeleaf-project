package com.example.aziz.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;  // Rôle de l'utilisateur (utilisation de l'énumération)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Collection<Client> clients;  // Liste des clients associés à cet utilisateur


    public User(String firstName, String lastName, Role role, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // On retourne un Set avec une seule autorité "ROLE_USER"
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // Getters et Setters
}

