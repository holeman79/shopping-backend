package com.holeman79.shoppingbackend.product.service;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapper;
import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperValue;
import com.holeman79.shoppingbackend.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final EnumMapper enumMapper;

    @GetMapping("/categories")
    public ResponseEntity<List<EnumMapperValue>> getCategoryList(){
        List<EnumMapperValue> categories = enumMapper.get("categories");
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/options")
    public ResponseEntity<Map<String, List<EnumMapperValue>>> getProductOptions(){
        Map<String, List<EnumMapperValue>> options = new LinkedHashMap();
        options.put("categories", enumMapper.get("categories"));
        options.put("colors", enumMapper.get("colors"));
        options.put("sizes", enumMapper.get("sizes"));

        return ResponseEntity.status(HttpStatus.OK).body(options);
    }

    @PostMapping
    public ResponseEntity<Long> addProduct(@ModelAttribute Product product) throws IOException {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@PageableDefault Pageable pageable, @RequestParam(name="category", required=false) String category){
        Page<Product> products = productService.getProductList(pageable, category);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
