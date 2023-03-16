package com.example.carwash.controllers;

import com.example.carwash.models.Product;
import com.example.carwash.models.User;
import com.example.carwash.models.enums.Role;
import com.example.carwash.repositories.ProductRepository;
import com.example.carwash.repositories.UserRepository;
import com.example.carwash.services.ProductService;
import com.example.carwash.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping("/admin/create")
    public String createProduct(Product product){
        productService.saveProduct(product);
        return"redirect:/admin";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("admin",userService.getUserByPrincipal(principal));
        if(title==null) title="";
        model.addAttribute("search",title);
        model.addAttribute("products",productService.listProduct(title));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id){
        userService.banUser(id);
        return"redirect:/admin";
    }
    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user,@RequestParam Map<String,String> form){
        userService.changeUserRoles(user,form);
        return "redirect:/admin";

    }

    @PostMapping("/product/record/{id}")
    public String userEdit(@PathVariable Long id){
     productService.addRecordtoSevenDays(id);
        return "redirect:/admin";

    }

}
