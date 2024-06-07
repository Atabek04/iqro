package kz.org.iqro.iqro.controllers;

import kz.org.iqro.iqro.entities.Role;
import kz.org.iqro.iqro.entities.User;
import kz.org.iqro.iqro.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final UserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register/user")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        System.out.println("Received registration request for user: " + user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }
}
