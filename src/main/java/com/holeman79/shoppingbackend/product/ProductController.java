package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.common.commoncode.CommonCode;
import com.holeman79.shoppingbackend.common.commoncode.CommonCodeService;
import com.holeman79.shoppingbackend.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product,
                                        @RequestPart("productFile") MultipartFile file,
                                        @RequestPart("productDetailFiles") MultipartFile[] detailFiles) throws Exception{
        Product savedProduct = productService.addProduct(product, file, detailFiles);
        //Product getProduct = productService.getProduct(savedProduct.getId());
        if(savedProduct != null)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        if(product != null)
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
