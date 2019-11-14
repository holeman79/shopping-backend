package com.holeman79.shoppingbackend.generic.code;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperType;

public enum PaymentType implements EnumMapperType {
    REMITTANCE("무통장입금"),
    KAKAO_PAY("카카오페이");

    private String value;

    PaymentType(String value){
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
