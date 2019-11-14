package com.holeman79.shoppingbackend.generic.code.convert;

import lombok.Getter;

@Getter
public class EnumMapperValue {

    private String key;
    private String value;

    public EnumMapperValue(EnumMapperType enumMapperType){
        key = enumMapperType.getKey();
        value = enumMapperType.getValue();
    }

}
