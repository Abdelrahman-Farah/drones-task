package com.elmenus.drones.service;

import com.elmenus.drones.entity.drone.Drone;

import java.util.List;

public interface DroneService {

    List<Drone> findALL();

    Drone save(Drone drone);
}
