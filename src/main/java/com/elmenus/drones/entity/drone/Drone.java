package com.elmenus.drones.entity.drone;

import com.elmenus.drones.entity.Medication;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @Column(name = "serial_number")
    @NotNull(message="this field is required")
    @Size(max=100, message="the serial number is 100 characters max")
    private String serialNumber;


    @NotNull(message="this field is required")
    @Enumerated(value = EnumType.STRING)
    private DroneModel model;


    @Column(name = "weight_limit")
    @NotNull(message="this field is required")
    @Min(value = 1, message = "the weight limit must be between 1 and 500")
    @Max(value = 500, message = "the weight limit must be between 1 and 500")
    private int weightLimit;

    @Column(name = "battery_capacity")
    @NotNull(message="this field is required")
    @Min(value = 0, message = "the battery capacity must be between 0 and 100")
    @Max(value = 100, message = "the battery capacity must be between 0 and 100")
    private int batteryCapacity;

    @NotNull(message="this field is required")
    @Enumerated(value = EnumType.STRING)
    private DroneState state;

    @OneToMany(mappedBy = "drone")
    private List<Medication> medications;

    public Drone(){

    }

    public Drone(String serialNumber, DroneModel model, int weightLimit, int batteryCapacity, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                '}';
    }
}
