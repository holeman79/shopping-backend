package com.holeman79.shoppingbackend.user;

import com.holeman79.config.security.JwtTokenProvider;
import com.holeman79.config.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.holeman79.exception.ErrorCode;
import com.holeman79.exception.OAuth2AuthenticationException;
import com.holeman79.exception.UserDuplException;
import com.holeman79.shoppingbackend.payload.LoginRequest;
import com.holeman79.shoppingbackend.payload.UserResponse;
import com.holeman79.shoppingbackend.user.domain.Role;
import com.holeman79.shoppingbackend.user.domain.User;
import com.holeman79.shoppingbackend.user.domain.enums.RoleType;
import com.holeman79.shoppingbackend.user.repository.UserRepository;
import com.holeman79.util.CookieUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    private final UserRepository userRepository;

    UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider
            ,UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }
    @GetMapping("/oauth")
    public ResponseEntity<UserResponse> getUser(HttpServletRequest request, HttpServletResponse response){
        String accessToken = CookieUtils.getCookie(request, OAuth2AuthenticationSuccessHandler.ACCESS_TOKEN)
                .map(Cookie::getValue).orElseThrow(() -> new OAuth2AuthenticationException(ErrorCode.ACCESS_TOKEN_NOT_EXIST));

        Long userId = tokenProvider.getUserIdFromJWT(accessToken);
        Optional<User> user = userRepository.findById(userId);
        CookieUtils.deleteCookie(request, response, OAuth2AuthenticationSuccessHandler.ACCESS_TOKEN);

        return new ResponseEntity<>(UserResponse.builder()
                .id(user.get().getId())
                .name(user.get().getName())
                .imageUrl(user.get().getImageUrl())
                .accessToken(accessToken)
                .roles(user.get().getRoles())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserId(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(user.getId());

        return new ResponseEntity<>(UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .accessToken(accessToken)
                .roles(user.getRoles())
                .build(), HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if(userRepository.existsByUserId(user.getUserId()))
            throw new UserDuplException(user.getUserId());

        // Creating user's account
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        Role userRole = new Role(RoleType.USER);
        user.setRoles(Collections.singleton(userRole));
        User result = userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
