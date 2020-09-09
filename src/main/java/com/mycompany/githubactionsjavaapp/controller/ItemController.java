package com.mycompany.githubactionsjavaapp.controller;

import com.mycompany.githubactionsjavaapp.model.Item;
import com.mycompany.githubactionsjavaapp.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        log.info("Adding item: {}", item);
        itemService.addItem(item);
        log.info("Successfully created item: {}", item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") String id){
        log.info("Fetching item with id: {}", id);
        Item item = itemService.getItem(UUID.fromString(id));
        log.info("Fetched item: {}", item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getItems(){
        log.info("Fetching list of items...");
        List<Item> items = itemService.getItems();
        log.info("Fetched list of items: {}", items);
        return ResponseEntity.ok(items);
    }

    @PutMapping
    public ResponseEntity<?> updateItem(Item item){
        log.info("Updating item: {}", item);
        itemService.updateItem(item);
        log.info("Updated item: {}", item);
        return ResponseEntity.noContent().build();
    }
}
