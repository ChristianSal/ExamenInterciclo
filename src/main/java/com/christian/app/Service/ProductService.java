package com.christian.app.Service;

import com.christian.app.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    public Iterable<Product> findAll();
    public Page<Product> findAll(Pageable pageable);
    public Optional<Product> findById(Long id);
    public Product save(Product product);
    public void deleteById(Long id);
}
