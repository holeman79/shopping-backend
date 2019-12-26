package com.holeman79.shoppingbackend.product.service;

import com.holeman79.shoppingbackend.file.UploadFileUtils;
import com.holeman79.shoppingbackend.generic.code.Category;
import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(Product product) throws IOException {
        Product savedProduct = productRepository.save(product);
        UploadFileUtils.uploadProductImages(savedProduct);
        return savedProduct;
    }

    public Product getProduct(Long id){
        Product product = productRepository.getOne(id);
        product.setSavedUri();
        product.setValue();
        return product;
    }

    public Page<Product> getProductList(Pageable pageable, String category){
        pageable = getPageable(pageable);
        Page<Product> products;
        if(category == null) products = productRepository.findAll(pageable);
        else products = productRepository.findByCategory(pageable, Category.valueOf(category));
        for(Product product : products) product.setSavedUri();
        return products;
    }

    private Pageable getPageable(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize()-1);
        return pageable;
    }

}
