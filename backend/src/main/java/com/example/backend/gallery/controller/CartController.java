package com.example.backend.gallery.controller;


import com.example.backend.gallery.entity.Cart;
import com.example.backend.gallery.entity.Item;
import com.example.backend.gallery.repository.CartRepository;
import com.example.backend.gallery.repository.ItemRepository;
import com.example.backend.gallery.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {

    private final CartRepository cartRepository;
    private final JwtService jwtService;
    private final ItemRepository itemRepository;

    public CartController(CartRepository cartRepository, JwtService jwtService, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.jwtService = jwtService;
        this.itemRepository = itemRepository;
    }


    @GetMapping("/api/cart/items")
    public ResponseEntity getItems(@CookieValue(value ="token",required = false) String token) {

        if(!jwtService.isValidToken(token)){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);

        List <Integer> itemIds = carts.stream().map(Cart::getItemId).collect(Collectors.toList());

        List <Item> items = itemRepository.findByIdIn(itemIds);

        return new ResponseEntity(items,HttpStatus.OK);

    }

    
    /*장바구니에 담기*/
    @PostMapping("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItem (@PathVariable("itemId") int itemId,
                                    @CookieValue(value = "token" , required = false) String token)
    {


        if(!jwtService.isValidToken(token)){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        int memberId = jwtService.getId(token);

        Cart cart =
        cartRepository.findByMemberIdAndItemId(memberId ,itemId);


        if(cart==null){

            Cart newCart = new Cart();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);

            cartRepository.save(newCart); //새로운 카트 추가

        }

        return new ResponseEntity<>(HttpStatus.OK);



    }


    /*장바구니에 담기*/
    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem (@PathVariable("itemId") int itemId,
                                              @CookieValue(value = "token" , required = false) String token)
    {


        if(!jwtService.isValidToken(token)){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        int memberId = jwtService.getId(token);

        Cart cart =
                cartRepository.findByMemberIdAndItemId(memberId ,itemId);


        cartRepository.delete(cart);


        return new ResponseEntity<>(HttpStatus.OK);



    }



}
