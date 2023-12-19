package com.elmenus.drones.dto;

import com.elmenus.drones.entity.drone.Drone;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MedicationDTO {
    @NotNull
    @Pattern(
            regexp = "[a-zA-Z_0-9-]+",
            message = "allowed only letters, numbers, ‘-‘, ‘_’"
    )
    private String name;

    @NotNull
    @Min(value = 1, message = "the weight must be between 1 and 500")
    @Max(value = 500, message = "the weight must be between 1 and 500")
    private int weight;

    @NotNull
    @Pattern(
            regexp = "[A-Z0-9_]+",
            message = "allowed only upper case letters, underscore and numbers"
    )
    private String code;

    private String image;

    @NotNull
    private String drone_sn;

    public MedicationDTO(String name, int weight, String code, String image, String drone_sn) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.drone_sn = drone_sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDrone_sn() {
        return drone_sn;
    }

    public void setDrone_sn(String drone_sn) {
        this.drone_sn = drone_sn;
    }

    @Override
    public String toString() {
        return "MedicationDTO{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                ", drone_sn='" + drone_sn + '\'' +
                '}';
    }
}
