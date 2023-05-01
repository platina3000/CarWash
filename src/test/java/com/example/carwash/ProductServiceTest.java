package com.example.carwash;


import com.example.carwash.models.Product;
import com.example.carwash.services.LogService;
import com.example.carwash.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-db.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testEmpty() {
        // Проверяем, что сервис не равен null
        assertNotNull(productService);
        // Вызываем метод сервиса и проверяем результат
        List<Product> result = productService.listProduct("");
        assertEquals(0, result.size());
    }


    @Test
    public void testAddProduct() {
        // Проверяем, что сервис не равен null
        assertNotNull(productService);

       Product p = new Product();
       p.setTitle("1");
       p.setDescription("1");
       p.setPrice(1);
       p.setType(2);

       productService.saveProduct(p);
        List<Product> result = productService.listProduct("");
        assertEquals(1, result.size());
    }

    @Test
    public void testRemoveProduct() {
        // Проверяем, что сервис не равен null
        assertNotNull(productService);

        Product p = new Product();
        p.setTitle("1");
        p.setDescription("1");
        p.setPrice(1);
        p.setType(2);

        productService.saveProduct(p);
        List<Product> result = productService.listProduct("");
        productService.deleteProduct(result.get(0).getId());
        result = productService.listProduct("");

        assertEquals(0, result.size());
    }

    @Test
    public void testStatistic() {
        // Проверяем, что сервис не равен null
        assertNotNull(productService);
        List<Integer> result =   productService.statistics();
        int sum=0;
        for (int i = 0; i < result.size() ; i++) {
            sum+=result.get(i);
        }

        assertEquals(8, result.size());
        assertEquals(0, sum);

    }



}
