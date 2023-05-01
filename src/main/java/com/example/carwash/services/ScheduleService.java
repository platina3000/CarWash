package com.example.carwash.services;

import com.example.carwash.models.Product;
import com.example.carwash.models.Schedule;
import com.example.carwash.models.User;
import com.example.carwash.repositories.ProductRepository;
import com.example.carwash.repositories.ScheduleRepository;
import com.example.carwash.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LogService logService;

    public List<Schedule> listRecordsByProduct(Long id) { //список записей на продукт

        return scheduleRepository.findAllByProduct(productService.getProductById(id));
    }

    public List<Schedule> listRecords() { //все записи

        return scheduleRepository.findAll();
    }


    public List<Schedule> listFreeRecords() {
        List<Schedule> list = new ArrayList<>();
        for (Schedule record : listRecords()) {
            if (record.getUser() == null && isAfterNow(record.getDate())) list.add(record);

        }
        return list;
    }

    public List<Schedule> listFreeRecords(String date) {
        List<Schedule> list = new ArrayList<>();
        if(date!=null&& !date.equals("")){
        for (Schedule record : listRecords()) {
            if (record.getUser() == null && isAfterNow(record.getDate())) list.add(record);

        }


        LocalDate day = LocalDate.parse(date);



        List<Schedule> recordsForDay = list.stream()
                .filter(record -> record.getDate().toLocalDate().equals(day))
                .toList();
        list=recordsForDay;}

        return list;
    }



    public boolean isAfterNow(LocalDateTime localDateTime) {
        return localDateTime.isAfter(LocalDateTime.now());
    }

    public boolean isBeforeNow(LocalDateTime localDateTime) {
        return localDateTime.isBefore(LocalDateTime.now());
    }



    public List<Schedule> listRecordsAfterNow(User user) {
        List<Schedule> userRecords = user.getScheduleList();
        List<Schedule> resultRecords = new ArrayList<>();
        for (Schedule record : userRecords) {
            if (isAfterNow(record.getDate())) resultRecords.add(record);
        }

        return resultRecords;

    }


    public List<Schedule> listRecordsBeforeNow(User user) {
        List<Schedule> userRecords = user.getScheduleList();
        List<Schedule> resultRecords = new ArrayList<>();
        for (Schedule record : userRecords) {
            if (isBeforeNow(record.getDate())) resultRecords.add(record);
        }
        return resultRecords;
    }

    public void addRecordtoSevenDays() {
        LocalDateTime lastDate = getLastDate();
        if (lastDate == null)
            lastDate = LocalDateTime.now();
        if (lastDate.isBefore(LocalDateTime.now()))
            lastDate = LocalDateTime.now();
        LocalDateTime regTime = null;

        for (int i = 0; i < 7; i++) {
            regTime = lastDate.plusDays(i + 1);
            regTime = LocalDateTime.of(regTime.getYear(), regTime.getMonth(), regTime.getDayOfMonth(), 6, 0);
            for (int j = 0; j < 20; j++) {
                Schedule s = new Schedule();
                s.setDate(regTime);
                scheduleRepository.save(s);
                regTime = regTime.plusMinutes(30);
            }
        }
        logService.addLog("ADD RECORD FOR 7 DAYS (until "+ getLastDate()+")");
    }

    public LocalDateTime getLastDate() {
        List<Schedule> l = scheduleRepository.findAll();
        if (l.size() < 1) {
            return null;
        }

        return l.get(l.size() - 1).getDate();
    }

    public String dateToStr(LocalDateTime date) {
        if (date == null) return "ЗАПИСИ НЕТ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    public void enroll(Long idP, Long idR, User user) {

        Schedule record = scheduleRepository.findById(idR).orElse(null);
        Product product = productRepository.findById(idP).orElse(null);
        record.setProduct(product);
        record.setUser(user);
        product.getScheduleList().add(record);
        user.getScheduleList().add(record);
        scheduleRepository.save(record);
        userRepository.save(user);
        productRepository.save(product);


        logService.addLog("ADD RECORD FOR "+ user.getEmail()+" to" +product.getTitle() +" at"+record.getDate());
    }

    public void cancel(Long id) {
        Schedule s = scheduleRepository.findById(id).orElse(null);
        Product p = s.getProduct();
        User u = s.getUser();

        u.getScheduleList().remove(s);
        userRepository.save(u);
        p.getScheduleList().remove(s);
        productRepository.save(p);
        s.setUser(null);
        s.setProduct(null);
        scheduleRepository.save(s);

        logService.addLog("CANCEL RECORD FOR "+ u.getEmail()+" to" +p.getTitle() +" at"+s.getDate());

    }
}
