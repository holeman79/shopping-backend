package com.holeman79.shoppingbackend.product.repository;

import com.holeman79.shoppingbackend.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
