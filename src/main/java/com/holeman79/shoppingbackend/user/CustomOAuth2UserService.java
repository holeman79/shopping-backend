package com.holeman79.shoppingbackend.user;

import com.holeman79.config.security.oauth2.OAuth2UserInfoFactory;
import com.holeman79.shoppingbackend.user.domain.User;
import com.holeman79.shoppingbackend.generic.code.SocialType;
import com.holeman79.shoppingbackend.user.domain.oauth2.OAuth2UserInfo;
import com.holeman79.shoppingbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        Optional<User> userOptional = userRepository.findBySocialId(oAuth2UserInfo.getId());
        return userOptional.map((user) -> updateExistingUser(user, oAuth2UserInfo))
                .orElseGet(()->registerNewUser(oAuth2UserRequest, oAuth2UserInfo));
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo){
        User user = User.builder()
                .socialType(SocialType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()))
                .socialId(oAuth2UserInfo.getId())
                .roleType(User.RoleType.USER)
                .name(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .createdDate(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        existingUser.setUpdatedDate(LocalDateTime.now());
        return userRepository.save(existingUser);
    }

}
