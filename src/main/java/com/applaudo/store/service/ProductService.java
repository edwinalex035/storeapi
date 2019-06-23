package com.applaudo.store.service;

import com.applaudo.store.domain.model.Product;
import com.applaudo.store.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public List<Product> list(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    public List<Product> list(int page, int size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size,
                Sort.by(direction.equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC,
                        orderBy));
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
