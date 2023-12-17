package com.elmenus.drones.controller;

import com.elmenus.drones.entity.Medication;
import com.elmenus.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;


    @Autowired
    public MedicationController (MedicationService medicationService){
        this.medicationService = medicationService;
    }

    @GetMapping("/")
    public List<Medication> listAllMedications(){
        return medicationService.findALL();
    }

}
