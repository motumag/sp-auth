package com.motuma.security.repository;

import com.motuma.security.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Boolean existsByCategory(String name);
}
