package com.elmenus.drones.service.impl;

import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.repository.DroneRepository;
import com.elmenus.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository){
        this.droneRepository = droneRepository;
    }

    @Override
    public List<Drone> findALL() {
        return droneRepository.findAll();
    }

    @Override
    public Drone save(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Drone findById(String id) {
        Optional<Drone> drone = droneRepository.findById(id);

        return drone.orElse(null);
    }
}
