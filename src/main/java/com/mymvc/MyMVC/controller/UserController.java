package com.mymvc.MyMVC.controller;

import com.mymvc.MyMVC.entities.User;
import com.mymvc.MyMVC.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String signUp(User user) {
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(Model model, User user) {
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Provided id does not exist: " + id));
        userRepo.deleteById(id);
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        final User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID doesn't exist: " + id));
        model.addAttribute("user", user);
        return "update-User";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, Model model, User user, BindingResult result) {
        if (result.hasErrors()){
            model.addAttribute("user",user);
            return "update-User";
        }
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

}
