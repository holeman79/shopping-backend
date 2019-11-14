package com.holeman79.shoppingbackend.generic.code;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperType;

public enum PhoneFirstNumber implements EnumMapperType {
    _010("010"),
    _011("011"),
    _016("016"),
    _019("019");

    private String value;

    PhoneFirstNumber(String value){
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
