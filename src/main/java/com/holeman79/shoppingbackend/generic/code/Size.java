package com.holeman79.shoppingbackend.generic.code;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperType;

public enum Size implements EnumMapperType {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    XLARGE("XL");

    private String value;

    Size(String value){
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
