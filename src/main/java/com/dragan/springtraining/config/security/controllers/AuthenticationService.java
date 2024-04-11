package com.dragan.springtraining.config.security.controllers;

import com.dragan.springtraining.config.security.JwtUtils;
import com.dragan.springtraining.user.entity.User;
import com.dragan.springtraining.user.exceptions.UserExistsException;
import com.dragan.springtraining.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserExistsException(request.email());
        }

        var user = new User();
        user.setFirstName( request.firstName() );
        user.setLastName( request.lastName() );
        user.setEmail( request.email() );
        user.setPassword( passwordEncoder.encode(request.password()));
        user.setRole( request.role() );

        userRepository.save(user);

        var jwtToken = jwtUtils.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //authenticates or throws exception
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()));

        var user = userRepository.findByEmail(request.email()).orElseThrow();

        var jwtToken = jwtUtils.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
