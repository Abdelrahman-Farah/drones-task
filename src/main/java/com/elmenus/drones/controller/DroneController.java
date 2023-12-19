package com.elmenus.drones.controller;

import com.elmenus.drones.dto.ValidationErrorResponse;
import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.entity.drone.DroneState;
import com.elmenus.drones.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        if (drone.getState() == DroneState.LOADING && drone.getBatteryCapacity() < 25){
            var map = new HashMap<>();
            map.put("state", "Can not register a drone with LOADING state if the battery level is below 25%");
            return ResponseEntity.badRequest().body(map);
        }

        droneService.save(drone);
        return ResponseEntity.ok(drone);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableDrones (){
        List<Drone> ready = new ArrayList<>();
        ready.addAll(droneService.getAllByState(DroneState.IDLE));
        ready.addAll(droneService.getAllByState(DroneState.LOADING));
        return ResponseEntity.ok(ready);
    }

    @GetMapping("/{drone_sn}")
    public ResponseEntity<?> getDroneInfo (@PathVariable("drone_sn") String drone_sn){
        Drone drone = droneService.findById(drone_sn);
        if(drone == null){
            return ResponseEntity.badRequest().body("You entered Invalid drone serial number");
        }
        return ResponseEntity.ok(drone);
    }

    @GetMapping("/{drone_sn}/battery")
    public ResponseEntity<?> getDroneBattery (@PathVariable("drone_sn") String drone_sn){
        Drone drone = droneService.findById(drone_sn);
        if(drone == null){
            return ResponseEntity.badRequest().body("You entered Invalid drone serial number");
        }
        var map = new HashMap<>();
        map.put("batteryCapacity", drone.getBatteryCapacity());
        return ResponseEntity.ok(map);
    }


    @ExceptionHandler
    public ResponseEntity<?>handle(HttpMessageNotReadableException e){
        return ResponseEntity.badRequest().body("You entered Invalid values");
    }
}
