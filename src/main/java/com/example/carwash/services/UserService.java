package com.example.carwash.services;

import com.example.carwash.models.User;
import com.example.carwash.models.enums.Role;
import com.example.carwash.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final LogService logService;

    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);

        log.info("SAVING NEW USER WITH EMAIL: {}",user.getEmail());
        logService.addLog("SAVINg NEW USER WITH EMAIL: "+user.getEmail());


        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal==null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()){
                user.setActive(false);
                log.info("Ban user with id={}; email={}", user.getId(), user.getEmail());
                logService.addLog("Ban user with email "+ user.getEmail());

            }
            else{
                user.setActive(true);
                log.info("UNBan user with id={}; email={}", user.getId(), user.getEmail());
                logService.addLog("Unban user with email "+ user.getEmail());
            }


            userRepository.save(user);
        }
    }

    public void changeUserRoles(User user, Map<String, String> form) {

        Set<String> roles= Arrays.stream(Role.values())
                .map(Role::name)//каждую роль в строку
                .collect(Collectors.toSet());
        user.getRoles().clear();
        System.out.println(roles);

        for(String key : form.values()){
            System.out.println(key);
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        logService.addLog("Change user role with email "+ user.getEmail());
        userRepository.save(user);
    }
}
