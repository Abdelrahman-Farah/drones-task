package com.elmenus.drones.service.impl;

import com.elmenus.drones.entity.Medication;
import com.elmenus.drones.repository.MedicationRepository;
import com.elmenus.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository){
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> findALL() {
        return medicationRepository.findAll();
    }
}
