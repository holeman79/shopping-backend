package com.holeman79.shoppingbackend.payload;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private String userId;
    private String accessToken;
    private String tokenType = "Bearer";
    private Collection roles;

    @Builder
    public UserResponse(Long id, String userId, String name, String imageUrl, String accessToken, Collection roles){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.roles = roles;
    }
}
