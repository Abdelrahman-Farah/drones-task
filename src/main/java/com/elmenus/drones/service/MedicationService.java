package com.elmenus.drones.service;

import com.elmenus.drones.dto.MedicationDTO;
import com.elmenus.drones.entity.Medication;
import com.elmenus.drones.entity.drone.Drone;

import java.util.List;

public interface MedicationService {
    List<Medication> findALL();

    Medication save(MedicationDTO medicationDTO, Drone drone);
}
