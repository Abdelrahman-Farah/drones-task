package com.elmenus.drones.service;

import com.elmenus.drones.dto.MedicationDTO;
import com.elmenus.drones.entity.Medication;
import com.elmenus.drones.entity.drone.Drone;
import com.elmenus.drones.entity.drone.DroneModel;
import com.elmenus.drones.entity.drone.DroneState;
import com.elmenus.drones.repository.DroneRepository;
import com.elmenus.drones.repository.MedicationRepository;
import com.elmenus.drones.service.impl.DroneServiceImpl;
import com.elmenus.drones.service.impl.MedicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class MedicationServiceTest {

    private Drone drone = new Drone("SN_123", DroneModel.LIGHTWEIGHT, 450, 80, DroneState.IDLE);

    private MedicationDTO medicationDTO = new MedicationDTO("MED_12", 250, "MED_12", null, "SN_123");

    @Mock
    private MedicationRepository medicationRepository;
    private MedicationService medicationService;


    @BeforeEach
    void setUp(){
        medicationService = new MedicationServiceImpl(medicationRepository);
    }

    @Test
    void findALL() {
        medicationService.findALL();
        verify(medicationRepository).findAll();
    }

    @Test
    void save() {
        Medication expected = medicationService.save(medicationDTO, drone);

        verify(medicationRepository).save(Mockito.any());
    }
}