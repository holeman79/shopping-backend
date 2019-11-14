package com.holeman79.shoppingbackend.user.domain;

import com.holeman79.shoppingbackend.generic.code.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class User implements UserDetails, OAuth2User {
    public enum RoleType { USER, SELLER, ADMIN };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_INPUT_ID")
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NAME")
    private String name;

    @NaturalId
    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "SOCIAL_ID")
    private String socialId;

    @Column(name = "SOCIAL_TYPE")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_TYPE")
    private RoleType roleType;

    @Transient
    private Map<String, Object> attributes;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;


    @Builder
    public User(Long id, String userId, String name, String password, String email, String imageUrl, SocialType socialType, String socialId, RoleType roleType, Map<String, Object> attributes, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.socialType = socialType;
        this.socialId = socialId;
        this.roleType = roleType;
        this.attributes = attributes;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return name;
    }
}
