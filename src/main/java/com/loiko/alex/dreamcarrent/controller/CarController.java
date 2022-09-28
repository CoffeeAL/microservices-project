package com.loiko.alex.dreamcarrent.controller;

import com.loiko.alex.dreamcarrent.model.Car;
import com.loiko.alex.dreamcarrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                linkTo(methodOn(CarController.class).saveCar(car)).withRel("saveCar"),
                linkTo(methodOn(CarController.class).updateCar(car)).withRel("updateCar"),
                linkTo(methodOn(CarController.class).deleteCar(car.getCarId())).withRel("deleteCar"));
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car request) {
        return ResponseEntity.ok(carService.save(request));
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car request) {
        return ResponseEntity.ok(carService.update(request));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") String carId) {
        return ResponseEntity.ok(carService.delete(carId));
    }
}