package com.example.carwash.controllers;

import com.example.carwash.models.Product;
import com.example.carwash.models.User;
import com.example.carwash.models.enums.Role;
import com.example.carwash.repositories.ProductRepository;
import com.example.carwash.repositories.ScheduleRepository;
import com.example.carwash.repositories.UserRepository;
import com.example.carwash.services.ProductService;
import com.example.carwash.services.ScheduleService;
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
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @PostMapping("/admin/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("admin", userService.getUserByPrincipal(principal));
        if (title == null) title = "";
        model.addAttribute("search", title);
        model.addAttribute("lastDate", scheduleService.dateToStr(scheduleService.getLastDate()));
        model.addAttribute("products", productService.listProduct(title));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form, Principal principal) {
        System.out.println(form);
        userService.changeUserRoles(user, form);
        if(userService.getUserByPrincipal(principal).getRoles().contains(Role.ROLE_USER))  return "redirect:/" ;
        return "redirect:/admin";

    }

    @PostMapping("/admin/product/record")  //открыть запись на неделю
    public String userEdit() {
        scheduleService.addRecordtoSevenDays();
        return "redirect:/admin";

    }

    @GetMapping("/admin/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("records", scheduleService.listRecordsByProduct(id));
        return "admin-product-info";
    }

    @GetMapping("/admin/user/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("nextRecords", scheduleService.listRecordsAfterNow(user));
        model.addAttribute("previousRecords", scheduleService.listRecordsBeforeNow(user));
        return "admin-user-info";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }


    @PostMapping("/admin/product/available/{id}")
    public String changeProductAvaliable(@PathVariable Long id) {
        productService.changeAvaliableStatus(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/record/cancel/{id}")  //отменить услугу
    public String cancelService(@PathVariable Long id, Principal principal, Model model) {
        User u = scheduleRepository.findById(id).orElse(null).getUser();
        scheduleService.cancel(id);
        return "redirect:/admin/user/"+u.getId();
    }
}
