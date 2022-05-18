package com.example.splitmycostsapi.security;

import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userEntity.getRole());

        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(grantedAuthority)
                .build();
    }
}
