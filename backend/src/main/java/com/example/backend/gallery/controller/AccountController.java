package com.example.backend.gallery.controller;


import com.example.backend.gallery.entity.Member;
import com.example.backend.gallery.repository.ItemRepository;
import com.example.backend.gallery.repository.MemberRepository;
import com.example.backend.gallery.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@RestController
public class AccountController {

    private final MemberRepository memberRepository;

    private final JwtService jwtService;

    public AccountController(MemberRepository memberRepository,JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String,String> params, HttpServletResponse response){
       Member member= memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

       if(member!=null){

           int id = member.getId();

           String token =
           jwtService.getToken("id",id);

           Cookie cookie = new Cookie("token",token);
           cookie.setHttpOnly(true); //자바스크릡트로 쿠키 접근 못하도록
           cookie.setPath("/");

           response.addCookie(cookie);

           return new ResponseEntity<>(id,HttpStatus.OK);

       }

       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value="token",required=false)String token){

        Claims claims = jwtService.getClaims(token);

        if(claims!=null){
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return  new ResponseEntity<>(null,HttpStatus.OK);
    }

}
