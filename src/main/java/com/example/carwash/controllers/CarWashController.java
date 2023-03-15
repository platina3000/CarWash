package com.example.carwash.controllers;


import com.example.carwash.models.Product;
import com.example.carwash.services.ProductService;
import com.example.carwash.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CarWashController {


    private final ProductService productService;
private final UserService userService;


    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title,Principal principal, Model model) {
        model.addAttribute("products",productService.listProduct(title));
        if(title==null) title="";
        model.addAttribute("search",title);
        model.addAttribute("user",userService.getUserByPrincipal(principal));
        return "services";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id,  Model model){

        model.addAttribute("product",productService.getProductById(id));
        return"product-info";

    }
    @PostMapping("/create")
    public String createProduct(Product product){
        productService.saveProduct(product);
        return"redirect:/";

    }
@PostMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
