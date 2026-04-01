package com.fridgeMate.fridgemate.service;

import com.fridgeMate.fridgemate.entity.FridgeItem;
import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.repository.FridgeItemEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FridgeItemService {

    @Autowired
    private FridgeItemEntryRepository fridgeItemEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(FridgeItem fridgeItem, String userName) {
        User user = userService.findByUserName(userName);
        FridgeItem saved = fridgeItemEntryRepository.save(fridgeItem);
        user.getFridgeItems().add(saved);
        userService.saveEntry(user);
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

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getFridgeItems().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        fridgeItemEntryRepository.deleteById(id);
    }

}
