package com.example.carwash.services;

import com.example.carwash.models.Product;
import com.example.carwash.models.Schedule;
import com.example.carwash.repositories.ProductRepository;
import com.example.carwash.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ScheduleRepository scheduleRepository;


    public List<Product> listProduct(String title) {
        if(title != null ) if(!title.equals("")) return productRepository.findAllByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        log.info("SAVING NEW PRODUCT {}",product);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    public void addRecordtoSevenDays(Long id){
        Product p = productRepository.findById(id).orElse(null);
        LocalDateTime lastDate = p.getLastDate();
        LocalDateTime regTime=null;

            for (int i = 0; i < 7; i++) {


                regTime = p.getLastDate().plusDays(i+1);
               regTime=LocalDateTime.of(regTime.getYear(),regTime.getMonth(),regTime.getDayOfMonth(),6,0);
            for (int j = 0; j < 20; j++) {
                    Schedule s = new Schedule();
                    s.setProduct(p);
                    s.setDate(regTime);
                    scheduleRepository.save(s);
                    regTime= regTime.plusMinutes(30);
                }


            }
            p.setLastDate(regTime);
            productRepository.save(p);


    }
}
