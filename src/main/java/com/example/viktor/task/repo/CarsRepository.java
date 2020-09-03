package com.example.viktor.task.repo;

import com.example.viktor.task.models.Cars;
import org.springframework.data.repository.CrudRepository;

public interface CarsRepository extends CrudRepository<Cars, Long> {
}
