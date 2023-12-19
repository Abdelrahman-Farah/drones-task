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

        var messages = ValidationErrorResponse.serializeErrorResult(bindingResult);
        if (drone == null){
            messages.put("drone_sn", "Invalid drone serial number");
        }

        int sum = 0;
        List<Medication> medications = drone.getMedications();
        for(Medication medication : medications){
            sum += medication.getWeight();
        }
        int remaining = drone.getWeightLimit() - sum;

        if (remaining < medicationDTO.getWeight()){
            messages.put("drone_sn", "no enough space");
        }
        System.out.println(sum);
        System.out.println(drone.getWeightLimit());
        System.out.println(remaining);
        System.out.println(messages.size());


        if (!messages.isEmpty()){
            return ResponseEntity.badRequest().body(messages);
        }

        medicationService.save(medicationDTO, drone);
        return  ResponseEntity.ok(medicationDTO);
    }
}
