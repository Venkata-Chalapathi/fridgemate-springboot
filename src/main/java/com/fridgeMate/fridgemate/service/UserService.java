package com.fridgeMate.fridgemate.service;

import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

//    @Autowired
//    private FridgeItemEntryRepository fridgeItemEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveEntry(User user) {
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll(); 
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
