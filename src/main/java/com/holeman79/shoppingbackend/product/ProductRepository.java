package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.product.domain.Category;
import com.holeman79.shoppingbackend.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
