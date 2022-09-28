package com.loiko.alex.dreamcarrent.repository;

import com.loiko.alex.dreamcarrent.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {
}