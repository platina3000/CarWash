package com.example.carwash.controllers;

import com.example.carwash.models.Schedule;
import com.example.carwash.models.User;
import com.example.carwash.services.ScheduleService;
import com.example.carwash.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ScheduleService scheduleService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage","Пользователь с email:"+user.getEmail()+" уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user,Model model){
        model.addAttribute("user",user);
        model.addAttribute("nextRecords", scheduleService.listRecordsAfterNow(user));
        model.addAttribute("previousRecords",scheduleService.listRecordsBeforeNow(user));
        return "user-info";
    }

    @PostMapping("/user/enroll/{idR}/{idP}")
    public String enrollUser(@PathVariable("idP") Long idP,@PathVariable("idR") Long idR, Principal principal, Model model) {

        scheduleService.enroll(idP,idR,userService.getUserByPrincipal(principal));

        return "redirect:/";
    }
}
