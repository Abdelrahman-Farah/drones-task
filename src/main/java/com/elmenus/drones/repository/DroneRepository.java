package com.elmenus.drones.repository;

import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.entity.drone.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {
    List<Drone> getAllByState(DroneState droneState);
}
