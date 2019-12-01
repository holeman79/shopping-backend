package com.holeman79.config;

import com.holeman79.shoppingbackend.generic.code.*;
import com.holeman79.shoppingbackend.generic.code.convert.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class BeanConfig {

    @Bean
    public EnumMapper enumMapper(){
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("categories", Category.class);
        enumMapper.put("colors", Color.class);
        enumMapper.put("sizes", Size.class);
        enumMapper.put("paymentTypes", PaymentType.class);
        enumMapper.put("bankBooks", BankBook.class);
        enumMapper.put("phoneFirstNumbers", PhoneFirstNumber.class);

        return enumMapper;
    }

    @Bean
    public MultipartResolver multipartResolvers() {
        //return new CommonsMultipartResolver();
        return new StandardServletMultipartResolver();
    }
}
