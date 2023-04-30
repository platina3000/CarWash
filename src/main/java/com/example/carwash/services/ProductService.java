package com.example.carwash.services;

import com.example.carwash.models.Product;
import com.example.carwash.models.Schedule;
import com.example.carwash.repositories.ProductRepository;
import com.example.carwash.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ScheduleRepository scheduleRepository;
    private final LogService logService;


    public List<Product> listProduct(String title) {
        if (title != null) if (!title.equals("")) return productRepository.findAllByTitle(title);
        return productRepository.findAll();
    }

    public List<Product> listProductByType(String title,boolean available,int type) {
        if (title != null) if (!title.equals("")) return productRepository.findAllByTitleAndAvailableAndType(title,available,type);
        return productRepository.findAllByAvailableAndType(available,type);
    }


    public List<Product> listProduct(String title,boolean available) {
        if (title != null) if (!title.equals("")) return productRepository.findAllByTitleAndAvailable(title,available);
        return productRepository.findAllByAvailable(available);
    }

    public void saveProduct(Product product) {
        log.info("SAVING NEW PRODUCT {}", product);
        logService.addLog("SAVING NEW PRODUCT "+ product);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        logService.addLog("DELETING  PRODUCT "+ productRepository.findById(id));

        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }


    public void changeAvaliableStatus(Long id) {
        logService.addLog("CHANGE AVAILABLE STATUS FOR  PRODUCT "+ productRepository.findById(id));

        Product p = getProductById(id);
        if(p.isAvailable())p.setAvailable(false);
        else p.setAvailable(true);
        productRepository.save(p);

    }



    public List<Integer> statistics() {
        List<Product> products = productRepository.findAll();
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<Integer> res = new ArrayList<>();
        res.add(products.size());//всего
        //--------------------------------------------------------------------------------
        int num = 0;
        for (Product p : products) {
            if (p.isAvailable()) num++;

        }
        res.add(num);//доступные

        res.add(scheduleList.size()); //всего дат для записи

        //--------------------------------------------------------------------------------
        int b = 0;
        int c = 0;
        num = 0;
        int money = 0;
        for (Schedule t : scheduleList) {
            if (t.getUser() != null) {
                if (t.getProduct().getType()==1) {
                    b++;
                } else c++;
                num++;
                money += t.getProduct().getPrice();
            }

        }
        res.add(num); //ВСЕГО брони

        //--------------------------------------------------------------------------------
        num = 0;
        for (Schedule t : scheduleList) {
            if (t.getUser() != null && t.getDate().isAfter(LocalDateTime.now())) num++;
        }
        res.add(num);//активные брони

        res.add(money);//общий кэш

        res.add(b);//t1
        res.add(c);//t2

        return res;
    }
}
