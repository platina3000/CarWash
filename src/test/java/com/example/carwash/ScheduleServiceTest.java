package com.example.carwash;


import com.example.carwash.models.Schedule;
import com.example.carwash.models.User;
import com.example.carwash.services.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService myService;

    @Test
    public void testAfter() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);

        // Вызываем метод сервиса и проверяем результат
        boolean result = myService.isAfterNow(LocalDateTime.now().plusDays(1));
        assertEquals(true, result);
    }

    @Test
    public void testBefore() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);

        // Вызываем метод сервиса и проверяем результат
        boolean result = myService.isBeforeNow(LocalDateTime.now().plusDays(1));
        assertEquals(false, result);
    }

    @Test
    public void testFormat() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expected = date.format(formatter);
        // Вызываем метод сервиса и проверяем результат
        String result = myService.dateToStr(date);
        assertEquals(expected, result);
    }

    @Test
    @Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testSevenDaysRecord() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);

        LocalDateTime date =  myService.getLastDate();
        assertNull(date);
        myService.addRecordtoSevenDays();
        // Вызываем метод сервиса и проверяем результат
         date =  myService.getLastDate();

        List<Schedule> list = myService.listRecords();

        assertTrue(list.size()>0);
        assertNotNull(date);
    }

    @Test
    @Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testEmptySchedule() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);
List<Schedule> list = myService.listFreeRecords();
        assertEquals(0,list.size());

    }
    @Test
    @Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testEmptyUserSchedule() {
        // Проверяем, что сервис не равен null
        assertNotNull(myService);
        User u = new User();
        List<Schedule> list = myService.listRecordsBeforeNow(u);
        assertEquals(0,list.size());
        list = myService.listRecordsAfterNow(u);
        assertEquals(0,list.size());

    }


}
