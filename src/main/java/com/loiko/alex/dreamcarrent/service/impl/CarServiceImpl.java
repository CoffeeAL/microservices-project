package com.loiko.alex.dreamcarrent.service.impl;

import com.loiko.alex.dreamcarrent.config.ServiceConfig;
import com.loiko.alex.dreamcarrent.model.Car;
import com.loiko.alex.dreamcarrent.repository.CarRepository;
import com.loiko.alex.dreamcarrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private MessageSource messages;

    @Autowired
    private CarRepository repository;

    @Autowired
    ServiceConfig config;

    @Override
    public Car findById(String id) {
        var maybeCar = repository.findById(id);
        if (maybeCar.isEmpty()) {
            throw new IllegalArgumentException(String.format(messages.getMessage("car.search.error.message", null, null), id));
        }
        var car = maybeCar.get();
        return car.withComment(config.getProperty());
    }

    @Override
    public Car save(Car car) {
        car.setCarId(UUID.randomUUID().toString());
        return update(car);
    }

    @Override
    public Car update(Car car) {
        repository.save(car);
        return car.withComment(config.getProperty());
    }

    @Override
    public String delete(String id) {
        var car = new Car();
        car.setCarId(id);
        repository.delete(car);
        return String.format(messages.getMessage("car.delete.message", null, null), id);
    }
}