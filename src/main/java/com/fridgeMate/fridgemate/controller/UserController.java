package com.fridgeMate.fridgemate.controller;

import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping

    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public Optional<User> findById(@PathVariable ObjectId id){
        return userService.findById(id);
    }
    @DeleteMapping("id/{id}")
    public void deleteById(@PathVariable ObjectId id){
        userService.deleteById(id);
    }

    @PutMapping("{userName}")

    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);

        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassWord(user.getPassWord());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
