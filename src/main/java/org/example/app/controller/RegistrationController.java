package org.example.app.controller;

import org.example.app.model.Role;
import org.example.app.model.User;
import org.example.app.repository.RoleRepository;
import org.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("register")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String setRegisterPage(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(roles);

        userRepository.save(user);

        return "redirect:/login";
    }
}
