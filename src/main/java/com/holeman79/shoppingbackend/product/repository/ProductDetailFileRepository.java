package com.holeman79.shoppingbackend.product.repository;

import com.holeman79.shoppingbackend.product.domain.ProductDetailFile;
import com.holeman79.shoppingbackend.product.domain.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailFileRepository extends JpaRepository<ProductDetailFile, Long> {
}
