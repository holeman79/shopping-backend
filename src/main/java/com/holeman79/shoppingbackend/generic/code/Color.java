package com.holeman79.shoppingbackend.generic.code;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperType;

public enum Color implements EnumMapperType {
    RED("레드"),
    GREEN("그린"),
    BLUE("블루"),
    BLACK("블랙"),
    WHITE("화이트"),
    IVORY("아이보리"),
    KHAKI("카키"),
    CAMEL("카멜"),
    NAVY("네이비"),
    YELLOW("옐로");

    private String value;

    Color(String value){
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
