package com.fridgeMate.fridgemate.controller;

import com.fridgeMate.fridgemate.entity.FridgeItem;
import com.fridgeMate.fridgemate.entity.User;
import com.fridgeMate.fridgemate.service.FridgeItemService;
import com.fridgeMate.fridgemate.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fridge")
public class FridgeItemControllerV2 {

    @Autowired
    private FridgeItemService fridgeItemService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName) {
        try {
            User user = userService.findByUserName(userName);
            List<FridgeItem> all = user.getFridgeItems();

            if(all != null && !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            if(all.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ignored){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody FridgeItem fridgeItem, @PathVariable String userName) {
        try {
            fridgeItemService.saveEntry(fridgeItem, userName);
            return new ResponseEntity<>(fridgeItem, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getFridgeItemById( @PathVariable ObjectId id ) {
        try {

            Optional<FridgeItem> fridgeItem = fridgeItemService.findById(id);
            if(fridgeItem.isPresent()){
                return new ResponseEntity<>(fridgeItem.get(), HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{userName}/{id}")
    public ResponseEntity<?> deleteFridgeItemById( @PathVariable ObjectId id, @PathVariable String userName ) {
        try {
            fridgeItemService.deleteById(id, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("id/{id}")
    public ResponseEntity<?> updateFridgeItemById(@PathVariable ObjectId id, @RequestBody FridgeItem newItem){
        try {
            FridgeItem oldItem = fridgeItemService.findById(id).orElse(null);

            if(oldItem != null) {
                oldItem.setName(newItem.getName() != null && !newItem.getName().equals("") ? newItem.getName() : oldItem.getName());
                oldItem.setCategory(newItem.getCategory() != null && !newItem.getCategory().equals("") ? newItem.getCategory() : oldItem.getCategory());
                oldItem.setQuantity(newItem.getQuantity() != 0 ? newItem.getQuantity() : oldItem.getQuantity());
                oldItem.setExpiryDate(newItem.getExpiryDate() != null ? newItem.getExpiryDate() : oldItem.getExpiryDate());
                fridgeItemService.saveEntry(oldItem);
                return new ResponseEntity<>(oldItem, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


}
