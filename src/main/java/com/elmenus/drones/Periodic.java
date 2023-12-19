package com.elmenus.drones;
import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Periodic {

    private static final Logger logger = LoggerFactory.getLogger(Periodic.class);

    private final DroneService droneService;

    @Autowired
    public Periodic(DroneService droneService) {
        this.droneService = droneService;
    }

    @Scheduled(fixedRate = 60000) // Execute every 1 minute
    public void myTask() {
        var drones = droneService.findALL();
        List<Integer> batteries = new ArrayList<>();
        for (Drone drone : drones){
            batteries.add(drone.getBatteryCapacity());
        }
        logger.info("Batteries levels: {}", batteries);
    }
}