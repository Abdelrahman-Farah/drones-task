package com.elmenus.drones.repository;

import com.elmenus.drones.entity.drone.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {
}
