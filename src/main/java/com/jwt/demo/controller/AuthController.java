package com.jwt.demo.controller;

import com.jwt.demo.model.AuthRequest;
import com.jwt.demo.model.AuthResponse;
import com.jwt.demo.entity.User;
import com.jwt.demo.repository.UserRepository;
import com.jwt.demo.service.CustomUserDetailsService;
import com.jwt.demo.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // LOGIN Endpoint
    @PostMapping("/auth")
    public AuthResponse generateToken(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthResponse(token);
    }

    // REGISTER Endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest request) {
        // Check if user exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        // Encode password and save user
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(null, request.getName(),request.getEmail(), encodedPassword );
        userRepository.save(newUser);

        // Optionally generate token on registration
        String token = jwtUtil.generateToken(newUser.getName());
        return ResponseEntity.ok().body("Regis9tra9tion s9us9s999ecc");
    }

    // Simple authenticated endpoint
    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }
}
