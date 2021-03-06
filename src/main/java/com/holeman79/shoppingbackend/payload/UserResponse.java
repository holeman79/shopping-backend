package com.holeman79.shoppingbackend.payload;

import com.holeman79.shoppingbackend.user.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private String userId;
    private String accessToken;
    private String tokenType = "Bearer";
    private Role role;

    @Builder
    public UserResponse(Long id, String userId, String name, String imageUrl, String accessToken, Role role){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.role = role;
    }
}
