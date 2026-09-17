package com.jashan.queue_forge.models;
import java.util.Collection;
import java.util.List;

import com.jashan.queue_forge.enums.ProviderType;
import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;





@Entity 
@Table(indexes = {@Index(name = "provider_Id_provider_Type",columnList = "providerId, providerType")})
public class Users implements UserDetails {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer userId;
    @JoinColumn(unique = true)
    private String userName;
    private String password;
    private String providerId;
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getUsername() {

       return this.userName;
    }
    
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    public ProviderType getProviderType() {
        return providerType;
    }
    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }

    

}
