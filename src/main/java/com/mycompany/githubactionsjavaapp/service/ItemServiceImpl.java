package com.mycompany.githubactionsjavaapp.service;

import com.mycompany.githubactionsjavaapp.exception.ItemNotFoundException;
import com.mycompany.githubactionsjavaapp.model.Item;
import com.mycompany.githubactionsjavaapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getItem(UUID id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        throw new ItemNotFoundException("Item not found");
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void removeItem(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}
