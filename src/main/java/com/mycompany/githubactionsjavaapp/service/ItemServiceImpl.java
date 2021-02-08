package com.mycompany.githubactionsjavaapp.service;

import com.mycompany.githubactionsjavaapp.exception.ItemNotFoundException;
import com.mycompany.githubactionsjavaapp.model.Item;
import com.mycompany.githubactionsjavaapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void addItem(Item item) {
        log.info("Adding item to database: {}", item);
        itemRepository.save(item);
    }

    @Override
    public Item getItem(UUID id) {
        log.info("Fetching item from database with id: {}", id);
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        throw new ItemNotFoundException("Item not found");
    }

    @Override
    public void updateItem(Item item) {
        log.info("Updating item: {} in database", item);
        itemRepository.save(item);
    }

    @Override
    public void removeItem(UUID id) {
        log.info("Removing item with id: {} from database", id);
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItems() {
        log.info("Getting items from database");
        return itemRepository.findAll();
    }
}
