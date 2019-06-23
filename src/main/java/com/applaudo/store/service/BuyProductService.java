package com.applaudo.store.service;

import com.applaudo.store.domain.model.BuyProduct;
import com.applaudo.store.domain.model.Product;
import com.applaudo.store.domain.model.User;
import com.applaudo.store.domain.repository.BuyProductRepository;
import com.applaudo.store.domain.repository.ProductRepository;
import com.applaudo.store.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("buyProductService")
public class BuyProductService {
    @Autowired
    private BuyProductRepository buyProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public BuyProduct purchase(final BuyProduct order, String username) throws Exception{
        System.out.println("username: " + username);
        BuyProduct finalOrder = null;
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(order.getProductId()).get();

        // Validating is stock available
        int qtyAvailable = product.getStock() - order.getQuantity();
        if(qtyAvailable >= 0) {
            // Updating product stock
            finalOrder = new BuyProduct();
            finalOrder.setQuantity(order.getQuantity());
            finalOrder.setUser(user);
            finalOrder.setProduct(product);
            buyProductRepository.save(finalOrder);

            product.setStock(qtyAvailable);
            productRepository.save(product);

            return finalOrder;
        } else {
            throw new Exception("There is not enough stock of the product");
        }
    }
}
