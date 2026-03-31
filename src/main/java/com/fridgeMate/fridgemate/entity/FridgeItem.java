package com.fridgeMate.fridgemate.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "fridgeItem_entries")
@Data
public class FridgeItem {
    @Id
    private ObjectId id;

    private String name;
    private String category;
    private int quantity;
    private LocalDate expiryDate;


}
