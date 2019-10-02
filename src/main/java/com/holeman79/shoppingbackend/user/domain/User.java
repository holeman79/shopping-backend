package com.holeman79.shoppingbackend.user.domain;

import com.holeman79.shoppingbackend.user.domain.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User implements UserDetails, OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    private String userId;

    @Size(max = 20)
    private String name;

    @NaturalId
    @Size(max = 40)
    @Email
    private String email;

    private String imageUrl;

    @Size(max = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @Transient
    private Map<String, Object> attributes;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public User() {

    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Builder
    public User(Long id, String userId, String name, String password, String email, String imageUrl, SocialType socialType, String socialId, Role role, Map<String, Object> attributes, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.socialType = socialType;
        this.socialId = socialId;
        this.role = role;
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
