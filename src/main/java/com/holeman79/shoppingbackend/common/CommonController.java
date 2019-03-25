package com.holeman79.shoppingbackend.common;

import com.holeman79.shoppingbackend.product.domain.Category;
import com.holeman79.shoppingbackend.product.domain.Color;
import com.holeman79.shoppingbackend.product.domain.Size;
import com.holeman79.shoppingbackend.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("/category")
    public ResponseEntity<?> getCategoryList(){
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
