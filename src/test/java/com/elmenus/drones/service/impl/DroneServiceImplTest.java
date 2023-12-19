package com.elmenus.drones.service.impl;

import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.entity.drone.DroneModel;
import com.elmenus.drones.entity.drone.DroneState;
import com.elmenus.drones.repository.DroneRepository;
import com.elmenus.drones.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    private Drone drone = new Drone("SN_123", DroneModel.LIGHTWEIGHT, 450, 80, DroneState.IDLE);

    @Mock private DroneRepository droneRepository;
    private DroneService droneService;

    @BeforeEach
    void setUp(){
        droneService = new DroneServiceImpl(droneRepository);
    }

    @Test
    void findALL() {
        droneService.findALL();
        verify(droneRepository).findAll();
    }

    @Test
    void save() {
        droneService.save(drone);
        verify(droneRepository).save(drone);
    }

    @Test
    void findById() {
        droneService.findById("SN_123");
        verify(droneRepository).findById("SN_123");
    }

    @Test
    void getAllByState() {
        droneService.getAllByState(DroneState.IDLE);
        verify(droneRepository).getAllByState(DroneState.IDLE);
    }
}