package org.example.hopital.security.service;

import lombok.AllArgsConstructor;
import org.example.hopital.security.entities.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
private AccountService accountService;

@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser appuser=accountService.loadUserByUsername(username);
        if(appuser==null) throw new UsernameNotFoundException(String.format("Username %s not found", username));
        String[] roles=appuser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails= User.withUsername(appuser.getUsername())
                .password(appuser.getPassword())
                .roles(roles).build();
         return userDetails;
    }
}
