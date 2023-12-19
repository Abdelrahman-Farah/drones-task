package com.elmenus.drones.service;

import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.entity.drone.DroneState;

import java.util.List;

public interface DroneService {

    List<Drone> findALL();

    Drone save(Drone drone);

    Drone findById(String id);

    List<Drone> getAllByState(DroneState droneState);
}
