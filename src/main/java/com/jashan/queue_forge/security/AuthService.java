package com.jashan.queue_forge.security;

import com.jashan.queue_forge.Repository.TechnicianRepository;
import com.jashan.queue_forge.enums.ProviderType;
import com.jashan.queue_forge.enums.RoleType;
import com.jashan.queue_forge.enums.TechnicianStatus;

import jakarta.transaction.Transactional;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.jashan.queue_forge.Repository.UserRepository;
import com.jashan.queue_forge.dto.LoginRequestDto;
import com.jashan.queue_forge.dto.LoginResponseDto;
import com.jashan.queue_forge.dto.SignupResponseDto;
import com.jashan.queue_forge.models.Technicians;
import com.jashan.queue_forge.models.Users;


@Service
public class AuthService {

    private final TechnicianRepository technicianRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtill authUtill;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AuthService(AuthenticationManager authenticationManager,
                AuthUtill authUtill,
                UserRepository userRepository,
                PasswordEncoder passwordEncoder, TechnicianRepository technicianRepository
    ){
        this.authUtill=authUtill;
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.technicianRepository = technicianRepository;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        Authentication authentication= authenticationManager.authenticate( // authenticate automatically calls customisedUserDetailService
                new UsernamePasswordAuthenticationToken(loginRequestDto.userName(),loginRequestDto.userPass()) );

        Users user= (Users)authentication.getPrincipal();

        String token=authUtill.generateAcccessToken(user);

        return new LoginResponseDto(token, user.getUserId());
    }

    public Users signUpInternal(LoginRequestDto signupRequestDto, ProviderType providerType, String providerId){

        Users user = userRepository.findByUserName(signupRequestDto.userName()).orElse(null);

        if (user!=null) throw new IllegalArgumentException("User already exists");

        user= new Users();
        user.setUserName(signupRequestDto.userName());
        user.setProviderId(providerId);
        user.setProviderType(providerType);
        user.setRoles(Set.of(RoleType.TECHNICIAN));
        
        if (providerType==ProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDto.userPass()));
        }
        user=userRepository.save(user);

        Technicians technician= new Technicians();
        technician.setTechnicianName(signupRequestDto.userName());
        technician.setTechnicianStatus(TechnicianStatus.AVAILABLE);
        technician.setUser(user);
    
        technicianRepository.save(technician);

        return user;

    }
    //controller
    public SignupResponseDto signup(LoginRequestDto signupRequestDto){

        Users user = signUpInternal(signupRequestDto, ProviderType.EMAIL, null);

        return new SignupResponseDto(user.getUserId(),user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOauthLoginRequests(OAuth2User oAuth2User, String registrationId){

        // fetch provider type and provider id

        ProviderType providerType=authUtill.getProviderType(registrationId);
        String providerId = authUtill.getProviderIdFromOAuth2User(oAuth2User,registrationId);

        // save both in user
        Users user=userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);

        String email=oAuth2User.getAttribute("email");
        Users emailUser=userRepository.findByUserName(email).orElse(null);

        if (user==null && emailUser==null){
            //sign up
            String userName=authUtill.determineUserName(oAuth2User,registrationId,providerId);

            user= signUpInternal(new LoginRequestDto(userName,null),providerType,providerId);

        } else if (user!=null) {

            if (email!=null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUserName(email);
                userRepository.save(user);
            }
        }else {
            throw new BadCredentialsException("Email already exist with provider " +emailUser.getProviderId());
        }

        //login
        LoginResponseDto loginResponseDto= new LoginResponseDto(authUtill.generateAcccessToken(user),user.getUserId());
        return ResponseEntity.ok(loginResponseDto);


    }

}
