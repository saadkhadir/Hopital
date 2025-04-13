package org.example.hopital.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {
    @Id
    private String userid;
    @Column(unique=true)
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch= FetchType.EAGER)//Avec LAZY ne charge les roles qui si on en a besoin
    private List<AppRole> roles;
}
