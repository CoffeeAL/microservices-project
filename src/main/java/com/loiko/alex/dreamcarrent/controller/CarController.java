package com.loiko.alex.dreamcarrent.controller;

import com.loiko.alex.dreamcarrent.model.Car;
import com.loiko.alex.dreamcarrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/{carId}")
    public ResponseEntity<Car> findCar(@PathVariable("carId") String carId) {
        Car car = carService.findById(carId);
        car.add(
                linkTo(methodOn(CarController.class).findCar(car.getCarId())).withSelfRel(),
                linkTo(methodOn(CarController.class).saveCar(car, null)).withRel("saveCar"),
                linkTo(methodOn(CarController.class).updateCar(car, null)).withRel("updateCar"),
                linkTo(methodOn(CarController.class).deleteCar(car.getCarId())).withRel("deleteCar"));
        return ResponseEntity.ok(car);
    }


    @PostMapping
    public ResponseEntity<String> saveCar(@RequestBody Car request, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(carService.save(request, locale));
    }

    @PutMapping
    public ResponseEntity<String> updateCar(@RequestBody Car request, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(carService.update(request, locale));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") String carId) {
        return ResponseEntity.ok(carService.delete(carId));
    }
}
