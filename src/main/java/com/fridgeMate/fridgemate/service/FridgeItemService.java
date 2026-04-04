package com.fridgeMate.fridgemate.service;

import com.fridgeMate.fridgemate.entity.FridgeItem;
import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.repository.FridgeItemEntryRepository;
import com.fridgeMate.fridgemate.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class FridgeItemService {

    @Autowired
    private FridgeItemEntryRepository fridgeItemEntryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveEntry(FridgeItem fridgeItem, String userName) {
        try {
            User user = userService.findByUserName(userName);
            FridgeItem saved = fridgeItemEntryRepository.save(fridgeItem);
            user.getFridgeItems().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving an entry " + e );
        }
    }

    public void saveEntry(FridgeItem fridgeItem) {
        fridgeItemEntryRepository.save(fridgeItem);
    }

    public List<FridgeItem> getAll(){
        return fridgeItemEntryRepository.findAll();
    }

    public Optional<FridgeItem> findById(ObjectId id) {
        return fridgeItemEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getFridgeItems().removeIf(x -> x.getId().equals(id));

            if(removed){
                userService.saveUser(user);
                fridgeItemEntryRepository.deleteById(id);
            }
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting an entry : " + e);
        }
        return removed;
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
