package com.loiko.alex.dreamcarrent.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@Getter
@Setter
@ToString
public class Car extends RepresentationModel<Car> {

    @Id
    @Column(name = "car_id", nullable = false)
    private String carId;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "color")
    private String color;

    @Column(name = "engine_volume")
    private String engineVolume;

    @Column(name = "comment")
    private String comment;

    public Car withComment(String comment) {
        setComment(comment);
        return this;
    }
}