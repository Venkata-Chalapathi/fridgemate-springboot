package com.fridgeMate.fridgemate.Controller;

import com.fridgeMate.fridgemate.Entity.FridgeItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/fridge")
public class FridgeItemController {

    @GetMapping("/abc")
    public String working(){
        return "Yes Working..";
    }

    private HashMap<Long, FridgeItem> fridgeItems = new HashMap<>();


    @GetMapping
    public List<FridgeItem> getAll() {
        return new ArrayList<>(fridgeItems.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody FridgeItem item) {
        fridgeItems.put(item.getId(), item);
        return true;
    }

    @GetMapping("id/{id}")
    public FridgeItem getFridgeItemById( @PathVariable long id ) {
        return fridgeItems.get(id);
    }
    @DeleteMapping("id/{id}")
    public FridgeItem deleteFridgeItemById( @PathVariable long id ) {
        return fridgeItems.remove(id);
    }
    @PutMapping("id/{id}")
    public FridgeItem updateFridgeItemById(@PathVariable long id, @RequestBody FridgeItem item){
        return fridgeItems.put(id, item);
    }




}
