package com.holeman79.shoppingbackend.product.repository;

import com.holeman79.shoppingbackend.product.domain.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
}
