package org.example.hopital.security;

import lombok.AllArgsConstructor;
import org.example.hopital.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImpl userDetailServiceImpl;
    @Bean
    public JdbcUserDetailsManager jdbcuserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


     //@Bean
     public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
         return new InMemoryUserDetailsManager(
                 User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("User").build(),
                 User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("User").build(),
                 User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("User","ADMIN").build()

         );
     }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .rememberMe(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/notAutorized")
                );
                httpSecurity.userDetailsService(userDetailServiceImpl);
        return httpSecurity.build();
    }



}
