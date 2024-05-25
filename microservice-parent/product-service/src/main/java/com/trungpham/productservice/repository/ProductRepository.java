package com.trungpham.productservice.repository;

import com.trungpham.productservice.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
