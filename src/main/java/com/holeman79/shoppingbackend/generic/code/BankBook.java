package com.holeman79.shoppingbackend.generic.code;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperType;

public enum BankBook implements EnumMapperType {
    HANA("하나은행", "444-999999-333444", "(주)쇼핑몰"),
    SHINHAN("신한은행", "110-222-888888", "(주)쇼핑몰"),
    WOORI("우리은행", "1111-101-999999", "(주)쇼핑몰");

    private String bankName;
    private String account;
    private String holder;

    BankBook(String bankName, String account, String holder){
        this.bankName = bankName;
        this.account = account;
        this.holder = holder;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return new StringBuilder().append(bankName)
                .append(':')
                .append(account)
                .append(" ")
                .append(holder).toString();
    }
}
