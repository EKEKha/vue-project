package com.example.backend.gallery.service;

import io.jsonwebtoken.Claims;

public interface JwtService {

     String getToken(String key, Object value);

     Claims getClaims(String token);

     boolean isValidToken(String token); //토큰이 문제가 없느지?

     int getId(String token);
}
