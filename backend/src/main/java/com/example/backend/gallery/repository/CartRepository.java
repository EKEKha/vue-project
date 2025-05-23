package com.example.backend.gallery.repository;

import com.example.backend.gallery.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartRepository extends JpaRepository <Cart,Integer>{


    List<Cart> findByMemberId(int memberId);

    Cart findByMemberIdAndItemId(int memberId,int itemId);

}
