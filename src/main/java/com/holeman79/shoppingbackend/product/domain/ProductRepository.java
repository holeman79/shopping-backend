package com.holeman79.shoppingbackend.product.domain;
import com.holeman79.shoppingbackend.generic.code.Category;
import com.holeman79.shoppingbackend.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(Pageable pageable, Category category);
}
