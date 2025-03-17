package org.example.hopital;

import org.example.hopital.entities.Patient;
import org.example.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
