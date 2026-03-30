package com.fridgeMate.fridgemate.controller;

import com.fridgeMate.fridgemate.entity.FridgeItem;
import com.fridgeMate.fridgemate.service.FridgeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/fridge")
public class FridgeItemControllerV2 {

    @Autowired
    private FridgeItemService fridgeItemService;

    @PostMapping
    public boolean createEntry(@RequestBody FridgeItem fridgeItem) {
        fridgeItemService.saveEntry(fridgeItem);
        return true;
    }

    @GetMapping("id/{id}")
    public FridgeItem getFridgeItemById( @PathVariable long id ) {
        return null;
    }
    @DeleteMapping("id/{id}")
    public FridgeItem deleteFridgeItemById( @PathVariable long id ) {
        return null;
    }
    @PutMapping("id/{id}")
    public FridgeItem updateFridgeItemById(@PathVariable long id, @RequestBody FridgeItem item){
        return null;
    }




}
