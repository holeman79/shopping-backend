package com.holeman79.config.security.oauth2;

import com.holeman79.exception.ExceptionMessageType;
import com.holeman79.exception.OAuth2AuthenticationProcessingException;
import com.holeman79.shoppingbackend.user.domain.oauth2.*;

import java.util.Map;

import static com.holeman79.shoppingbackend.user.domain.enums.SocialType.*;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(GOOGLE.getValue())){
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(FACEBOOK.getValue())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(GITHUB.getValue())) {
            return new GithubOAuth2UserInfo(attributes);
        }else if (registrationId.equalsIgnoreCase(KAKAO.getValue())) {
            return new KakaoOAuth2UserInfo(attributes);
        }else {
            throw new OAuth2AuthenticationProcessingException(registrationId + " " + ExceptionMessageType.NOT_SUPPORTED.getValue());
        }
    }
}
