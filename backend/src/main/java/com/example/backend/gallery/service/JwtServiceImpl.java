package com.example.backend.gallery.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtServiceImpl implements JwtService{


    private String secretKey="asdfasbasdf@#@#$ASDFAW$%lasdzzzzzzzzzasdff;asdf765%^&3423";


    @Override
    public String getToken(String key, Object value) {

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + (1000*60*5));
        byte [] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String,Object> claimMap = new HashMap<>();
        claimMap.put(key,value);

        JwtBuilder builder= Jwts.builder().setHeader(headerMap)
                .setClaims(claimMap)
                .setExpiration(expTime)
                .signWith(signingKey,SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {

        if(token !=null && !token.equals("")){

            try{
                byte [] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
                Key signingKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
                return claims;
            }catch (ExpiredJwtException e){
                //만료됨
            }catch (JwtException e){
                //유효하지 않음
            }

        }
        return null;
    }

    @Override
    public boolean isValidToken(String token) {
        return this.getClaims(token) !=null ;
    }

    @Override
    public int getId(String token) {

        Claims claims = this.getClaims(token);

        if(claims != null ){
            return Integer.parseInt(claims.getId().toString());
        }

        return 0;
    }
}
