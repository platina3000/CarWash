package com.example.carwash;

import com.example.carwash.services.LogService;
import com.example.carwash.services.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestPropertySource("/application-test.properties")
public class LogServiceTest {

    @Autowired
    private LogService myService;

    @Test
    public void testEmpty() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);

        // Вызываем метод сервиса и проверяем результат
        boolean result = myService.addLog("");
        assertEquals(false, result);
    }

    @Test
    public void testLog() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);

        // Вызываем метод сервиса и проверяем результат
        boolean result = myService.addLog("Test Log");
        assertEquals(true, result);
    }

}
