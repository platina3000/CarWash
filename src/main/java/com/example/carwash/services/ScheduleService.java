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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {
   private final ScheduleRepository scheduleRepository;
   private  final ProductService productService;
   private final ProductRepository productRepository;
   private final UserRepository userRepository;

    public List<Schedule> listRecordsByProduct(Long id) { //список записей на продукт

        return scheduleRepository.findAllByProduct(productService.getProductById(id));
    }

    public List<Schedule> listRecords() { //все записи

        return scheduleRepository.findAll();
    }

    public List<Schedule> listFreeRecords(){
        List<Schedule> list = new ArrayList<>();
        for (Schedule record: listRecords()) {
            if(record.getUser()==null ) list.add(record);

        }
        return list;
    }

    public boolean isAfterNow(LocalDateTime localDateTime){
       return   localDateTime.isAfter(LocalDateTime.now());
    }

    public void addRecordtoSevenDays() {


        LocalDateTime lastDate = getLastDate();
        if(lastDate==null)
            lastDate= LocalDateTime.now();
        if(lastDate.isBefore(LocalDateTime.now()))
            lastDate= LocalDateTime.now();
        LocalDateTime regTime = null;

        for (int i = 0; i < 7; i++) {
            regTime =lastDate.plusDays(i + 1);
            regTime = LocalDateTime.of(regTime.getYear(), regTime.getMonth(), regTime.getDayOfMonth(), 6, 0);
            for (int j = 0; j < 20; j++) {
                Schedule s = new Schedule();
                s.setDate(regTime);
                scheduleRepository.save(s);
                regTime = regTime.plusMinutes(30);
            }
        }
    }

    public LocalDateTime getLastDate(){
        List<Schedule> l = scheduleRepository.findAll();
        if(l.size()<1){
            return null;
        }

        return l.get(l.size()-1).getDate();
    }
    public String dateToStr(LocalDateTime date){
        if(date==null) return "ЗАПИСИ НЕТ";
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

    }
}
