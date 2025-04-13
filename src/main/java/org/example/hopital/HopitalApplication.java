package org.example.hopital;

import org.example.hopital.entities.Patient;
import org.example.hopital.repository.PatientRepository;
import org.example.hopital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
    @Autowired
  private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Saad",new Date(),true,123));
        patientRepository.save(new Patient(null,"Walid",new Date(),false,144));
        patientRepository.save(new Patient(null,"Yassine",new Date(),true,180));
        patientRepository.save(new Patient(null,"Saad",new Date(),true,123));
        patientRepository.save(new Patient(null,"Walid",new Date(),false,144));
        patientRepository.save(new Patient(null,"Yassine",new Date(),true,180));
        patientRepository.save(new Patient(null,"Saad",new Date(),true,123));
        patientRepository.save(new Patient(null,"Walid",new Date(),false,144));
        patientRepository.save(new Patient(null,"Yassine",new Date(),true,180));
        patientRepository.save(new Patient(null,"taha",new Date(),true,123));
        patientRepository.save(new Patient(null,"Walid",new Date(),false,144));
        patientRepository.save(new Patient(null,"Yassine",new Date(),true,180));

    }

   // @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        return args -> {
            UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u1==null)
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build()
            );
            UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u2==null)
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user11").password(passwordEncoder().encode("1234")).roles("USER").build()
            );
            UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(u3==null)
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build()
            );
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("null","user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("null","user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("null","admin","1234","admin@gmail.com","1234");

        accountService.addRoleToUser("user1","USER");
        accountService.addRoleToUser("user2","USER");
        accountService.addRoleToUser("admin","USER");
        };
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
