package com.holeman79.shoppingbackend.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String email;
    private Collection authorities;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
