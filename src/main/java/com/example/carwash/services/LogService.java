package com.example.carwash.services;

import com.example.carwash.models.Log;
import com.example.carwash.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    public boolean addLog(String info){
        if(info.isEmpty()) return false;
        Log msg = new Log();
        msg.setDate(LocalDateTime.now());
        msg.setInfo(info);
        logRepository.save(msg);
        return true;
    }
}
