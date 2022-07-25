package com.example.spring_boot_crud.controller;

import com.example.spring_boot_crud.model.User;
import com.example.spring_boot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "all-users";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/user-delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/user-update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(long id, User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
