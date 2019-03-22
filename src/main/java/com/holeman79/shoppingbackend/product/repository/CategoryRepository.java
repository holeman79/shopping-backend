package com.holeman79.shoppingbackend.product.repository;

import com.holeman79.shoppingbackend.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
