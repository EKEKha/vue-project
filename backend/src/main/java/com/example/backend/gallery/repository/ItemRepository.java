package com.example.backend.gallery.repository;

import com.example.backend.gallery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository <Item,Integer>{


    List <Item> findByIdIn(List<Integer> ids);

}
