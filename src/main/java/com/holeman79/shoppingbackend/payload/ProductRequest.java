package com.holeman79.shoppingbackend.payload;


import com.holeman79.shoppingbackend.product.domain.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest implements Serializable{

    private String title;
    private String price;
    private String savedMoneyRate;
    private List<Option> options;
    private String description;
}
