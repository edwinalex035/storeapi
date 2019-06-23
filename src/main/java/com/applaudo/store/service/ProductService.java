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
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return productRepository.findAll(sort);
    }

    public List<Product> list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    public List<Product> list(int page, int size, String orderBy, String direction) {
        String sortDirection = (direction.isEmpty() || direction.toLowerCase().equals("asc"))?
                "asc" : "desc";
        String sortOrderBy = (orderBy.toLowerCase().equals("name") || orderBy.toLowerCase().equals("popularity"))?
                orderBy.toLowerCase() : "name";
        Sort sort = Sort.by(sortDirection.equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC,
                sortOrderBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> findByName(String name, int page, int size) {
        return findByName(name, page, size, "", "");
    }

    public List<Product> findByName(String name, int page, int size, String orderBy, String direction) {
        String sortDirection = (direction.isEmpty() || direction.toLowerCase().equals("asc"))?
                "asc" : "desc";
        String sortOrderBy = (orderBy.toLowerCase().equals("name") || orderBy.toLowerCase().equals("popularity"))?
                orderBy.toLowerCase() : "name";
        Sort sort = Sort.by(sortDirection.equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC,
                sortOrderBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return productRepository.findByNameContaining(name, pageRequest);
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
