package com.jashan.queue_forge.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import com.jashan.queue_forge.enums.ProviderType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.jashan.queue_forge.models.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtill {

    private static final Logger log= LoggerFactory.getILoggerFactory().getLogger(AuthUtill.class.getName());

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

    //parsing token to retrive info

    public String getUserNameFromToken(String token){
        //stores token information
        Claims claims= Jwts.parser()
                        .verifyWith(getSecretkey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        
        return claims.getSubject();
    }

    // fetching provider type

    public ProviderType getProviderType(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google"-> ProviderType.GOOGLE;
            case "github"-> ProviderType.GITHUB;
            case "twitter"-> ProviderType.TWITTER;
            default -> throw new IllegalArgumentException("Unsupported Auth Provider"+ registrationId);

        };
    }

    // fetching provider id

    public String getProviderIdFromOAuth2User(OAuth2User oAuth2User, String registrationId){

        String providerId = switch (registrationId.toLowerCase()){
            case "google"-> oAuth2User.getAttribute("sub");
            case "github"-> oAuth2User.getAttribute("id").toString();
            default -> {
                log.error("Unsupported provider {}",registrationId);
                throw new IllegalArgumentException("Unsupported provider "+registrationId);
            }
        };
        if(providerId==null || providerId.isBlank()){
            log.error("Unable to find provider id for OAuth2 provider {}",registrationId);
            throw new IllegalArgumentException("Unable to find provider id for OAuth2 provider");
        }

        return providerId;
    }

    public String determineUserName(OAuth2User oAuth2User,String registrationId, String providerId){
        String email=oAuth2User.getAttribute("email");
        if(email!=null && !email.isBlank()){
            return email;
        }
        return switch(registrationId.toLowerCase()){
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
            default -> providerId;
        };
        
    }
}
