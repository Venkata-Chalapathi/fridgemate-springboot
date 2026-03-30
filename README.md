# FridgeMate - Smart Fridge & Pantry Tracker

A Spring Boot REST API to track fridge items and get expiry alerts.

## Tech Stack
- Java 21
- Spring Boot 4.0.5
- MongoDB
- Spring Data MongoDB
- Maven

## Features
- Add fridge items with expiry date
- View all fridge items
- Get item by ID
- Update item details
- Delete item
- Track expiry dates

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /fridge | Get all items |
| POST | /fridge | Add new item |
| GET | /fridge/{id} | Get item by ID |
| PUT | /fridge/{id} | Update item |
| DELETE | /fridge/{id} | Delete item |

## Sample Request
```json
{
    "name": "Milk",
    "category": "dairy",
    "quantity": 2,
    "expiryDate": "2026-04-05"
}
```

## Project Structure
```
src/main/java/com/fridgeMate/fridgemate/
├── controller/
│   └── FridgeItemControllerV2.java
├── entity/
│   └── FridgeItem.java
├── repository/
│   └── FridgeItemEntryRepository.java
├── service/
│   └── FridgeItemService.java
└── FridgemateApplication.java
```

## Coming Soon
- Spring Security + JWT authentication
- Expiry date alerts
- React frontend
- MySQL migration
