package com.loiko.alex.dreamcarrent.service;

import com.loiko.alex.dreamcarrent.model.Car;

public interface CarService {

    Car findById(String id);

    Car save(Car car);

    Car update(Car car);

    String delete(String id);
}