package org.example.hopital.security.service;

import jakarta.transaction.Transactional;
import org.example.hopital.security.entities.AppRole;
import org.example.hopital.security.entities.AppUser;
import org.example.hopital.security.repo.AppRoleRepository;
import org.example.hopital.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
private AppUserRepository appUserRepository;
private AppRoleRepository appRoleRepository;
private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
    this.appUserRepository = appUserRepository;
    this.appRoleRepository = appRoleRepository;
}
    @Override
    public AppUser addNewUser(String username, String password, String firstName, String email, String confirmPassword) {
        AppUser appuser=appUserRepository.findByUsername(username);
        if(appuser!=null) throw new RuntimeException("User exist ");
        if(!password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");
        appuser=AppUser.builder()
                .userid(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedUser=appUserRepository.save(appuser);

        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null) ;
        if(appRole!=null) throw new RuntimeException("Role exist ");
        appRole=AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
AppUser appUser=appUserRepository.findByUsername(username);
AppRole appRole=appRoleRepository.findById(role).get() ;
if(appUser!=null) throw new RuntimeException("User exist ");
appUser.getRoles().add(appRole);
//appUserRepository.save(appUser); pas besoin methode transactionnel , auto
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get() ;
        appUser.getRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
