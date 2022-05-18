package com.example.splitmycostsapi.security;

import com.example.splitmycostsapi.security.models.AuthRequest;
import com.example.splitmycostsapi.security.models.AuthResponse;
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

    @PostMapping("/api/auth/register")
    public UserEntity registerUser(@RequestBody SignUpRequest request){

        UserEntity user = new UserEntity(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        // Todo: the response includes the user password as well.
        //  Change this so that the password is not sent in the response.
        //  Also, send as a ResponseEntity object.
        return userRepository.save(user);
    }

    @PostMapping("/api/auth/authenticate")
    public AuthResponse authenticateUser(@RequestBody AuthRequest request){

        String jwt = "token";

        // Todo: implement user validation and token generation

        return new AuthResponse(jwt);
    }
}
