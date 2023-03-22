package com.example.carwash.repositories;

import com.example.carwash.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
List<Product> findAllByTitle(String title);
List<Product> findAllByTitleAndAvailable(String title, boolean available);
List<Product> findAllByTitleAndAvailableAndType(String title, boolean available,int type);

    List<Product> findAllByAvailable(boolean available);
    List<Product> findAllByAvailableAndType(boolean available,int type);
}
