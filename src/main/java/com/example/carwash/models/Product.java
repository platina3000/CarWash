package com.example.carwash.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {               //услуга
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private int type;
    @Column(name = "price")
    private int price;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;


    @Column(name = "available")
    private boolean available;

    @PrePersist
    private void init() {
        available = false;
    }
    @OneToMany( fetch = FetchType.EAGER)
    private List<Schedule> scheduleList = new ArrayList<>();

}
