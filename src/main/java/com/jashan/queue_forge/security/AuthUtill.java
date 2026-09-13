package com.jashan.queue_forge.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jashan.queue_forge.models.Users;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component 
public class AuthUtill {

    // jwt tokken generation

    @Value ("${jwt.secretkey}")
    private String jwtSecretKey;

    //Header
    private SecretKey getSecretkey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8)); // Algorithem
    }

    //Body
    public String generateAcccessToken(Users user){
        return Jwts.builder()
        .subject(user.getUsername())
        .claim("user_id" ,user.getUserId().toString())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000*600))
        .signWith(getSecretkey())
        .compact();
    }

}
