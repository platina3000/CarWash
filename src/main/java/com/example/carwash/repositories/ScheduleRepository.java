package com.example.carwash.repositories;

import com.example.carwash.models.Product;
import com.example.carwash.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    public List<Schedule> findAllByProduct(Product p);
}
