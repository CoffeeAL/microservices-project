package com.loiko.alex.dreamcarrent.service.impl;

import com.loiko.alex.dreamcarrent.model.Car;
import com.loiko.alex.dreamcarrent.service.CarService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public Car findById(String id) {
        Car car = new Car();
        car.setId(new Random().nextInt(10_000));
        car.setCarId(id);
        car.setColor("black");
        car.setModel("Ford Mustang");
        car.setYear("1995");
        car.setEngineVolume("3.0");
        car.setComment("Not new Ford Mustang");
        return car;
    }

    @Override
    public String save(Car car, Locale locale) {
        String responseMessage = null;
        if (!StringUtils.isEmpty(car.toString())) {
            responseMessage = String.format(messageSource.getMessage("car.create.message", null, locale), car.toString());
        }
        return responseMessage;
    }

    @Override
    public String update(Car car, Locale locale) {
        String responseMessage = null;
        if (!StringUtils.isEmpty(car.toString())) {
            responseMessage = String.format(messageSource.getMessage("car.update.message", null, locale), car.toString());
        }
        return responseMessage;
    }

    @Override
    public String delete(String id) {
        return String.format(messageSource.getMessage("car.delete.message", null, null), id);
    }
}
