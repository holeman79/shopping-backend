package com.holeman79.shoppingbackend.auth;

import com.holeman79.shoppingbackend.common.domain.Role;
import com.holeman79.shoppingbackend.common.domain.RoleName;
import com.holeman79.shoppingbackend.exception.AppException;
import com.holeman79.shoppingbackend.payload.ApiResponse;
import com.holeman79.shoppingbackend.payload.JwtAuthenticationResponse;
import com.holeman79.shoppingbackend.payload.LoginRequest;
import com.holeman79.shoppingbackend.security.JwtTokenProvider;
import com.holeman79.shoppingbackend.security.UserPrincipal;
import com.holeman79.shoppingbackend.user.RoleRepository;
import com.holeman79.shoppingbackend.user.User;
import com.holeman79.shoppingbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(authentication);
        JwtAuthenticationResponse loginInfo = new JwtAuthenticationResponse(jwt);
        loginInfo.setUsername(userPrincipal.getUsername());
        loginInfo.setEmail(userPrincipal.getEmail());
        loginInfo.setAuthorities(userPrincipal.getAuthorities());

        return ResponseEntity.ok(loginInfo);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    @GetMapping("/checkLogin")
    public ResponseEntity<?> checkLogin(){
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
