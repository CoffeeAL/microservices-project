package com.loiko.alex.dreamcarrent.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class Car extends RepresentationModel<Car> {

    private int id;
    private String carId;
    private String model;
    private String year;
    private String color;
    private String engineVolume;
    private String comment;
}
