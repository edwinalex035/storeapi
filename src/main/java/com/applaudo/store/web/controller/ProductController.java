package com.applaudo.store.web.controller;

import com.applaudo.store.domain.model.Product;
import com.applaudo.store.domain.repository.ProductRepository;
import com.applaudo.store.service.ProductService;
import com.applaudo.store.web.exception.MyResourceNotFoundException;
import com.applaudo.store.web.util.RestPreconditions;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> list() {
        List<Product> products = productService.list();
        return products;
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity<List<Product>> list(@RequestParam("page") int page, @RequestParam("size") int size,
                                              UriComponentsBuilder uriComponentsBuilder, HttpServletResponse response) {
        List<Product> products = productService.list(page, size);
        return new ResponseEntity<>(products, null, HttpStatus.OK);
    }

    @GetMapping(params = { "page", "size", "orderBy", "direction"})
    public ResponseEntity<List<Product>> list(@RequestParam("page") int page, @RequestParam("size") int size,
                                              @RequestParam("orderBy") String orderBy, @RequestParam("direction") String direction,
                                              UriComponentsBuilder uriComponentsBuilder, HttpServletResponse response) {
        List<Product> products = productService.list(page, size, orderBy, direction);
        return new ResponseEntity<>(products, null, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            Product product = RestPreconditions.checkFound(productService.findById(id));
            return new ResponseEntity<>(product, null, HttpStatus.OK);
        } catch (MyResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Preconditions.checkNotNull(product);
        Product response = productService.create(product);
        return new ResponseEntity<>(response, null, HttpStatus.CREATED);
    }

    @PutMapping(value = "$(id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product product) {
        Preconditions.checkNotNull(product);
        Product response = productService.update(product);
        return new ResponseEntity<>(response, null, HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }


}
