package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.product.domain.Category;
import com.holeman79.shoppingbackend.product.domain.Color;
import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.domain.Size;
import com.holeman79.shoppingbackend.product.repository.CategoryRepository;
import com.holeman79.shoppingbackend.product.repository.ColorRepository;
import com.holeman79.shoppingbackend.product.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ColorRepository colorRepository;

    private final SizeRepository sizeRepository;

    private final CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<?> getCategoryList(){
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/option")
    public ResponseEntity<?> getOptionList(){
        Map result = new HashMap();
        List<Category> categories = categoryRepository.findAll();
        List<Color> colors = colorRepository.findAll();
        List<Size> sizes = sizeRepository.findAll();
        result.put("categories", categories);
        result.put("colors", colors);
        result.put("sizes", sizes);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestPart("product") Product product,
                                        @RequestPart("productFile") MultipartFile file,
                                        @RequestPart("productDetailFiles") MultipartFile[] detailFiles) throws Exception{
        Product savedProduct = productService.addProduct(product, file, detailFiles);
        if(savedProduct != null)
            return new ResponseEntity<Long>(savedProduct.getId(), HttpStatus.OK);
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
    public ResponseEntity<Page<Product>> getProductList(@PageableDefault Pageable pageable, @RequestParam(name="category", required=false) String categoryCode){
        Page<Product> productList = productService.getProductList(pageable, categoryCode);
        if(productList.getContent().size() > 0)
            return new ResponseEntity<Page<Product>>(productList, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
