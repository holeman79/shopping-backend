package com.holeman79.shoppingbackend.generic.code;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperType;

public enum Category implements EnumMapperType {
    OUTER("아우터"),
    KNIT("니트"),
    SHIRT("셔츠"),
    T_SHIRT("티셔츠"),
    PANTS("팬츠"),
    DENIM("데님"),
    ACCESSORY("악세사리"),
    ETC("기타잡화");

    private String value;

    Category(String value){
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
