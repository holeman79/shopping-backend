package com.holeman79.shoppingbackend.product;

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
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final EnumMapper enumMapper;

    @GetMapping("/options")
    public ResponseEntity<Map<String, List<EnumMapperValue>>> getCategoryColorSizeList(){
        Map<String, List<EnumMapperValue>> options = new LinkedHashMap<>();

        return null;
    }

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestPart("product") Product product,
                                        @RequestPart("productFile") MultipartFile file,
                                        @RequestPart("productDetailFiles") MultipartFile[] detailFiles) throws Exception{
        Product savedProduct = productService.addProduct(product, file, detailFiles);
        if(savedProduct != null)
            return new ResponseEntity<>(savedProduct.getId(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        if(product != null)
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@PageableDefault Pageable pageable, @RequestParam(name="category", required=false) String categoryCode){
        Page<Product> productList = productService.getProductList(pageable, categoryCode);
        if(productList.getContent().size() > 0)
            return new ResponseEntity<Page<Product>>(productList, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
