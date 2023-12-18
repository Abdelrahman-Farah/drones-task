package com.elmenus.drones.controller;

import com.elmenus.drones.dto.MedicationDTO;
import com.elmenus.drones.dto.ValidationErrorResponse;
import com.elmenus.drones.entity.Medication;
import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.service.DroneService;
import com.elmenus.drones.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final DroneService droneService;
    private final MedicationService medicationService;


    @Autowired
    public MedicationController (
            MedicationService medicationService,
            DroneService droneService
    ){
        this.medicationService = medicationService;
        this.droneService = droneService;
    }

    @GetMapping("/")
    public List<Medication> listAllMedications(){
        return medicationService.findALL();
    }

    @PostMapping("/attach")
    public ResponseEntity<?> attachNewMedication(
            @Valid @RequestBody MedicationDTO medicationDTO,
            BindingResult bindingResult
    ){
        Drone drone = droneService.findById(medicationDTO.getDrone_sn());
        if (bindingResult.hasErrors() || drone == null){
            var messages = ValidationErrorResponse.serializeErrorResult(bindingResult);
            if (drone == null){
                messages.put("drone_sn", "Invalid drone serial number");
            }
            return ResponseEntity.badRequest().body(messages);
        }
        System.out.println(medicationDTO);
        System.out.println(bindingResult);
        Medication medication = medicationService.save(medicationDTO, drone);
        return  ResponseEntity.ok(medicationDTO);
    }
}
