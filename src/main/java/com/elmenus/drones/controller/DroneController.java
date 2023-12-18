package com.elmenus.drones.controller;

import com.elmenus.drones.dto.ValidationErrorResponse;
import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {
    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService){
        this.droneService = droneService;
    }

    @GetMapping("/")
    public List<Drone> listAllDrones (){
        return droneService.findALL();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewDrone(
            @Valid @RequestBody Drone drone,
            BindingResult bindingResult
    ){

        if (bindingResult.hasErrors()){
            var messages = ValidationErrorResponse.serializeErrorResult(bindingResult);
            return ResponseEntity.badRequest().body(messages);
        }

        droneService.save(drone);
        return ResponseEntity.ok(drone);
    }


    @ExceptionHandler
    public ResponseEntity<?>handle(HttpMessageNotReadableException e){
        return ResponseEntity.badRequest().body("You entered Invalid values");
    }
}
