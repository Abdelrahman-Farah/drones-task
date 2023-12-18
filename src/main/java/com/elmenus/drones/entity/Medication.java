package com.elmenus.drones.entity;

import com.elmenus.drones.entity.drone.Drone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_sn")
    @JsonIgnore
    private Drone drone;

    public Medication() {
    }

    public Medication(String name, int weight, String code, String image, Drone drone) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.drone = drone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                ", drone=" + drone +
                '}';
    }
}
