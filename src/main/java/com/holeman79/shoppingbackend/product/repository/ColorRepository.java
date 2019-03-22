package com.holeman79.shoppingbackend.product.repository;

import com.holeman79.shoppingbackend.product.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
