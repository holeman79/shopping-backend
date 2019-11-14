package com.holeman79.shoppingbackend.generic.code;

public enum SocialType {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao"),
    GITHUB("github");

    private String value;

    SocialType(String value){
        this.value = value;
    }

    public String getValue() { return value; }

}
