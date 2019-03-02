package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProduct(Long id){
        return productRepository.getOne(id);
    }
}
