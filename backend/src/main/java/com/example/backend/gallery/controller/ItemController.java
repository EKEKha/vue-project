package com.example.backend.gallery.controller;


import com.example.backend.gallery.entity.Item;
import com.example.backend.gallery.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/api/items")
    public List<Item> getItem(){

        List <Item> items = itemRepository.findAll();

        return items;
    }

}
