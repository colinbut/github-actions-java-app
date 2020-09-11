package com.mycompany.githubactionsjavaapp.service;

import com.mycompany.githubactionsjavaapp.exception.ItemNotFoundException;
import com.mycompany.githubactionsjavaapp.model.Item;
import com.mycompany.githubactionsjavaapp.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService = new ItemServiceImpl();

    @Test
    public void testAddItem(){
        Item item = createItem("Handcream", BigDecimal.valueOf(3.56));
        Mockito.when(itemRepository.save(item)).thenReturn(item);

        itemService.addItem(item);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test
    public void getItem() {
        UUID id = UUID.randomUUID();
        Item item = createItem("Water", BigDecimal.ONE);
        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Item returnedItem = itemService.getItem(id);

        assertEquals(item, returnedItem);
    }

    @Test
    public void testGetItemNotFound() {
        UUID id = UUID.randomUUID();
        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> {itemService.getItem(id);});
    }

    @Test
    public void testRemoveItem() {
        UUID id = UUID.randomUUID();
        Mockito.doNothing().when(itemRepository).deleteById(id);

        itemService.removeItem(id);

        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testGetItems() {
        List<Item> items = new ArrayList<>();
        items.add(createItem("Phone", BigDecimal.valueOf(800.00)));
        items.add(createItem("Apple", BigDecimal.valueOf(0.50)));
        items.add(createItem("Jeans", BigDecimal.valueOf(10.00)));

        Mockito.when(itemRepository.findAll()).thenReturn(items);

        List<Item> returnedItems = itemService.getItems();

        assertEquals(items, returnedItems);
    }

    @Test
    public void testUpdateItem(){
        Item item = createItem("Mouse", BigDecimal.valueOf(5.00));
        Mockito.when(itemRepository.save(item)).thenReturn(item);

        itemService.updateItem(item);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    private Item createItem(String name, BigDecimal price){
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setPrice(price);
        return item;
    }
}
