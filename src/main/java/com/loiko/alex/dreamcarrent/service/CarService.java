package com.loiko.alex.dreamcarrent.service;

import com.loiko.alex.dreamcarrent.model.Car;

import java.util.Locale;

public interface CarService {

    Car findById(String id);

    String save(Car car, Locale locale);

    String update(Car car, Locale locale);

    String delete(String id);
}
