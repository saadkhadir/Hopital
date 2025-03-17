package org.example.hopital.web;


import lombok.AllArgsConstructor;
import org.example.hopital.entities.Patient;
import org.example.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class PatientController {

    private  PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Patient> patientList = patientRepository.findAll();
        model.addAttribute("Listpatients", patientList);

        return "patients";
    }
}
