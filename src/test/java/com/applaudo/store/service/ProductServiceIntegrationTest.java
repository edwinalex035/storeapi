package com.applaudo.store.service;

import com.applaudo.store.config.StoreConfig;
import com.applaudo.store.config.StoreH2Config;
import com.applaudo.store.domain.model.Product;
import com.applaudo.store.domain.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { StoreH2Config.class, StoreConfig.class},
        loader = AnnotationConfigContextLoader.class
)
public class ProductServiceIntegrationTest {
    @Resource
    private ProductService productService;

    @Test
    public void whenList_thenReturnProducts() {
        // given:
        List<Product> products = productService.list();
        assertEquals(4, products.size());

    }

    @Test
    public void whenListPageable_thenReturnProducts(){
        List<Product> products = productService.list(0, 2);
        assertEquals(2, products.size());
    }

    @Test
    public void whenListPageableSort_thenReturnProducts(){
        List<Product> products = productService.list(0, 2, "name", "asc");
        assertEquals(2, products.size());
        assertEquals("Oreo Cookies", products.get(0).getName());
    }
}
