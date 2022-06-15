package com.example.splitmycostsapi.security;

import com.example.splitmycostsapi.security.models.AuthRequest;
import com.example.splitmycostsapi.security.models.AuthResponse;
import com.example.splitmycostsapi.security.models.SignUpRequest;
import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/auth/register")
    public UserEntity registerUser(@RequestBody SignUpRequest request) {

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
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();

            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok()
                    .body(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
