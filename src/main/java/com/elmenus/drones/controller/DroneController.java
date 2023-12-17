package com.elmenus.drones.controller;

import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
