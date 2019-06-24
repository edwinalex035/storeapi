package com.applaudo.store.service;

import com.applaudo.store.domain.model.PriceLog;
import com.applaudo.store.domain.model.Product;
import com.applaudo.store.domain.repository.PriceLogRepository;
import com.applaudo.store.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;
    @Autowired
    PriceLogRepository priceLogRepository;

    public List<Product> list() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return productRepository.findAll(sort);
    }

    public List<Product> list(int page, int size) {
        page = (page < 0)? 0 : page;
        size = (size < 0)? 5 : size;
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    public List<Product> list(int page, int size, String orderBy, String direction) {
        page = (page < 0)? 0 : page;
        size = (size < 0)? 5 : size;
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
        page = (page < 0)? 0 : page;
        size = (size < 0)? 5 : size;
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

    @Transactional
    public Product update(Product product, String username) {
        PriceLog priceLog = new PriceLog();
        Product oldProduct = findById(product.getId());
        priceLog.setOldPrice(oldProduct.getPrice());
        oldProduct.setDescription(product.getDescription());
        priceLog.setNewPrice(product.getPrice());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStock(product.getStock());
        oldProduct = productRepository.save(oldProduct);

        // Saving price log
        priceLog.setIdProduct(oldProduct.getId());
        priceLog.setUsername(username);
        priceLog.setCreateDt(new Date());
        priceLog = priceLogRepository.save(priceLog);

        //Saving in log file
        logger.info("update(): Change in product price: " + priceLog);

        return oldProduct;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public void like(Long id) {
        Product product = findById(id);
        product.setPopularity(product.getPopularity() + 1);
        update(product);
    }

}
