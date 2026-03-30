package com.fridgeMate.fridgemate.repository;
import com.fridgeMate.fridgemate.entity.FridgeItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FridgeItemEntryRepository extends MongoRepository<FridgeItem, String >{



}
