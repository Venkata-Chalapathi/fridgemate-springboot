package com.fridgeMate.fridgemate.controller;


import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public  String healthCheck() {
        return  "Ok Working";
    }

    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user){
        userService.saveEntry(user);
        return true;
    }
}
