package com.fridgeMate.fridgemate.service;

import com.fridgeMate.fridgemate.entity.FridgeItem;
import com.fridgeMate.fridgemate.repository.FridgeItemEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FridgeItemService {

    @Autowired
    private FridgeItemEntryRepository fridgeItemEntryRepository;

    public void saveEntry(FridgeItem fridgeItem) {
        fridgeItemEntryRepository.save(fridgeItem);
    }
}
