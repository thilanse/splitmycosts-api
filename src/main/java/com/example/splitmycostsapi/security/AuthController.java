package com.example.splitmycostsapi.security;

import com.example.splitmycostsapi.security.models.SignUpRequest;
import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/signup")
    public UserEntity signup(@RequestBody SignUpRequest request){

        UserEntity user = new UserEntity(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        return userRepository.save(user);
    }
}
