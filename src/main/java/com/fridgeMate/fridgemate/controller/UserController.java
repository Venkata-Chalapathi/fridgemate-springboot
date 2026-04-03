package com.fridgeMate.fridgemate.controller;

import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.repository.UserRepository;
import com.fridgeMate.fridgemate.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
    private UserRepository userRepository;

    @GetMapping

//    public List<User> getAll(){
//        return userService.getAll();
//    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("id/{id}")
//    public Optional<User> findById(@PathVariable ObjectId id){
//        return userService.findById(id);
//    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @PutMapping

    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);

        userInDb.setUserName(user.getUserName());
        userInDb.setPassWord(user.getPassWord());
        userService.saveEntry(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
