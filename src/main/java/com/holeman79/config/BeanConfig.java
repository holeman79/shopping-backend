package com.holeman79.config;

import com.holeman79.shoppingbackend.generic.code.*;
import com.holeman79.shoppingbackend.generic.code.convert.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public EnumMapper enumMapper(){
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("CategoryType", Category.class);
        enumMapper.put("ColorOption", Color.class);
        enumMapper.put("SizeOption", Size.class);
        enumMapper.put("PaymentOption", PaymentType.class);
        enumMapper.put("BankBookOption", BankBook.class);
        enumMapper.put("PhoneFirstNumberOption", PhoneFirstNumber.class);

        return enumMapper;
    }
}
