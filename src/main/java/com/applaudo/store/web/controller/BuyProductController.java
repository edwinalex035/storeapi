package com.applaudo.store.web.controller;

import com.applaudo.store.domain.model.BuyProduct;
import com.applaudo.store.service.BuyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy")
public class BuyProductController {
    @Autowired
    BuyProductService buyProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public ResponseEntity<BuyProduct> purchase(@RequestBody BuyProduct buy, OAuth2Authentication authentication) {
        try {
            String username = (String) authentication.getUserAuthentication().getPrincipal();
            BuyProduct order = buyProductService.purchase(buy, username);
            return new ResponseEntity<>(order, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
