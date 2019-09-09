package com.holeman79.shoppingbackend.user.domain.oauth2;


import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {
    public static final String FACEBOOK_GRAPH_URL = "https://graph.facebook.com";
    public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return FACEBOOK_GRAPH_URL + "/" + (String) attributes.get("id") + "/picture?type=large";
    }
}
