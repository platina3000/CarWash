package com.example.carwash.repositories;

import com.example.carwash.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log,Long> {

    List<Log> findAllByDateAndInfo(LocalDateTime date, String info);
}
