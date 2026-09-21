package com.jashan.queue_forge.models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.jashan.queue_forge.enums.ProviderType;
import com.jashan.queue_forge.enums.RoleType;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @ElementCollection(fetch = FetchType.EAGER) // tells springboot to create a table of roles
    @Enumerated(EnumType.STRING)
    Set<RoleType> roles= new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("Role_"+ role.name()))
                .collect(Collectors.toSet());
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
    public Set<RoleType> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleType> roles) {
        this.roles = roles;
    }
    public Users(Integer userId, String userName, String password, String providerId, ProviderType providerType,
            Set<RoleType> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.providerId = providerId;
        this.providerType = providerType;
        this.roles = roles;
    }
    public Users() {
    }
    
    

}
